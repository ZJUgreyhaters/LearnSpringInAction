package com.quantchi.metadatamgr.data.entity;

import com.quantchi.common.Paging;
import com.quantchi.lineage.diff.Field;
import com.quantchi.lineage.diff.SchemaDiff;
import com.quantchi.metadatamgr.data.entity.MDSchemaDiffDetail.FieldModification.Op;
import com.quantchi.metadatamgr.data.mapper.MDFieldInfoDBMapper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author hetong.
 */
public class MDSchemaDiffDetail {

  private final List<FieldModification> modifications;
  private String datasourceId;
  private int sourceTableAmount;
  private int targetTableAmount;
  private int tableAdditionsAmount;
  private int tableDeletionsAmount;
  private int tableChangesAmount;
  private int sourceFieldAmount;
  private int targetFieldAmount;
  private int fieldAdditionsAmount;
  private int fieldDeletionsAmount;
  private int fieldChangesAmount;
  private int total;

  private MDSchemaDiffDetail(MDSchemaDiffDetailBuilder builder) {
    this.datasourceId = builder.datasourceId;
    this.modifications = builder.modifications;
    this.sourceFieldAmount = builder.sourceFieldAmount;
    this.targetFieldAmount = builder.targetFieldAmount;
    this.sourceTableAmount = builder.sourceTableAmount;
    this.targetTableAmount = builder.targetTableAmount;
    this.tableAdditionsAmount = builder.tableAdditionsAmount;
    this.tableDeletionsAmount = builder.tableDeletionsAmount;
    this.tableChangesAmount = builder.tableChangesAmount;
    this.fieldAdditionsAmount = builder.fieldAdditionsAmount;
    this.fieldDeletionsAmount = builder.fieldDeletionsAmount;
    this.fieldChangesAmount = builder.fieldChangesAmount;
    this.total = builder.total;
  }

  public int getSourceTableAmount() {
    return sourceTableAmount;
  }

  public int getTargetTableAmount() {
    return targetTableAmount;
  }

  public int getTableAdditionsAmount() {
    return tableAdditionsAmount;
  }

  public int getTableDeletionsAmount() {
    return tableDeletionsAmount;
  }

  public int getTableChangesAmount() {
    return tableChangesAmount;
  }

  public int getSourceFieldAmount() {
    return sourceFieldAmount;
  }

  public int getTargetFieldAmount() {
    return targetFieldAmount;
  }

  public int getFieldAdditionsAmount() {
    return fieldAdditionsAmount;
  }

  public int getFieldDeletionsAmount() {
    return fieldDeletionsAmount;
  }

  public int getFieldChangesAmount() {
    return fieldChangesAmount;
  }

  public List<FieldModification> getModifications() {
    return modifications;
  }

  public String getDatasourceId() {
    return datasourceId;
  }

  public int getTotal() {
    return total;
  }

  public static class MDSchemaDiffDetailBuilder {

    private List<FieldModification> modifications = new ArrayList<>();
    private String datasourceId;
    private int sourceTableAmount;
    private int targetTableAmount;
    private int tableAdditionsAmount;
    private int tableDeletionsAmount;
    private int tableChangesAmount;
    private int sourceFieldAmount;
    private int targetFieldAmount;
    private int fieldAdditionsAmount = 0;
    private int fieldDeletionsAmount = 0;
    private int fieldChangesAmount = 0;
    private int total;
    private MDFieldInfoDBMapper mdFieldInfoDBMapper;

    public MDSchemaDiffDetailBuilder(SchemaDiff diff, MDFieldInfoDBMapper mdFieldInfoDBMapper) {
      this.mdFieldInfoDBMapper = mdFieldInfoDBMapper;
      tableAdditionsAmount = diff.getAdditions().size();
      tableDeletionsAmount = diff.getDeletions().size();
      tableChangesAmount = diff.getChanges().size();

      diff.getAdditions().forEach(a -> addModification(a.getTableName(), a.getFields(), Op.ADD));
      diff.getDeletions().forEach(d -> addModification(d.getTableName(), d.getFields(), Op.DEL));
      diff.getChanges().forEach(tableDiff -> {
        addModification(tableDiff.getName(), tableDiff.getAdditions(), Op.ADD);
        addModification(tableDiff.getName(), tableDiff.getDeletions(), Op.DEL);
        fieldChangesAmount += tableDiff.getChanges().size();
        tableDiff.getChanges().forEach(c -> modifications.add(
            new FieldModification(tableDiff.getName(), c.getBefore().getFieldName(), Op.MOD)));
      });
      total = modifications.size();
    }

    public MDSchemaDiffDetailBuilder sourceTableAmount(int sourceTableAmount) {
      this.sourceTableAmount = sourceTableAmount;
      return this;
    }

    public MDSchemaDiffDetailBuilder targetTableAmount(int targetTableAmount) {
      this.targetTableAmount = targetTableAmount;
      return this;
    }

    public MDSchemaDiffDetailBuilder sourceFieldAmount(int sourceFieldAmount) {
      this.sourceFieldAmount = sourceFieldAmount;
      return this;
    }

    public MDSchemaDiffDetailBuilder targetFieldAmount(int targetFieldAmount) {
      this.targetFieldAmount = targetFieldAmount;
      return this;
    }

    public MDSchemaDiffDetailBuilder keyword(String keyword) {
      if (keyword != null) {
        modifications.removeIf(m -> !m.fieldName.contains(keyword));
      }
      return this;
    }

    public MDSchemaDiffDetailBuilder operationFilter(String op) {
      if (op != null && op.length() > 0) {
        modifications.removeIf(m -> m.operation != Op.valueOf(op));
      }
      return this;
    }

    public MDSchemaDiffDetailBuilder datasourceId(String datasourceId) {
      this.datasourceId = datasourceId;
      return this;
    }

    public MDSchemaDiffDetail build(int pageSize, int page) {
      if (datasourceId == null) {
        throw new IllegalStateException("datasource id cannot be null");
      }
      modifications = Paging.pagingPlug(modifications, pageSize, page);
      for (FieldModification m : modifications) {
        Matcher matcher = Pattern.compile("([^.]+)\\.([^.]+)\\.([^.]+)").matcher(m.fieldName);
        if (!matcher.matches()) {
          throw new IllegalArgumentException("field name must be in db.table.field format");
        }
        m.setFieldId(mdFieldInfoDBMapper
            .selectIdByName(datasourceId, matcher.group(1), matcher.group(2), matcher.group(3))
            .get(0));
      }
      return new MDSchemaDiffDetail(this);
    }

    private void addModification(String tableName, Collection<Field> fields, Op op) {
      switch (op) {
        case ADD: fieldAdditionsAmount += fields.size();
          break;
        case DEL: fieldDeletionsAmount += fields.size();
          break;
      }
      fields.forEach(f -> {
        modifications.add(new FieldModification(tableName, f.getFieldName(), op));
      });
    }

  }

  public static class FieldModification {

    /**
     * including db name and table name
     */
    private final String fieldName;
    private final Op operation;
    private final String desc;
    private String fieldId;

    FieldModification(String tableName, String fieldName, Op operation) {
      this.fieldName = tableName + "." + fieldName;
      this.operation = operation;
      if (operation == Op.MOD) {
        this.desc = "数据类型变化";
      } else {
        this.desc = null;
      }
    }

    public String getFieldName() {
      return fieldName;
    }

    public Op getOperation() {
      return operation;
    }

    public String getDesc() {
      return desc;
    }

    public String getFieldId() {
      return fieldId;
    }

    public void setFieldId(String fieldId) {
      this.fieldId = fieldId;
    }

    public enum Op {
      ADD, DEL, MOD
    }
  }
}
