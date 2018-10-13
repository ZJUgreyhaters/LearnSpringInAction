package com.quantchi.metadatamgr.extract;

import com.quantchi.lineage.diff.Field;
import com.quantchi.lineage.diff.Table;
import com.quantchi.metadatamgr.data.TableEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


public class CollectorExtractor extends BaseExtractor {

	private static final Logger logger = LoggerFactory.getLogger(CollectorExtractor.class);

	protected String sourceId = "";

	private List<TableEntity> tableList = new ArrayList<>();

	public String getSourceId() {
		return sourceId;
	}

	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}

	public List<TableEntity> getTableList() {
		return tableList;
	}

	public void setTableList(List<TableEntity> tableList) {
		this.tableList = tableList;
	}

	public Set<Table> toDiffTables(){
		Set<Table> tables = new HashSet<>();
		for(TableEntity tb:tableList){
			Set<Field> fields = tb.getFieldEntityList().stream().map(i-> new Field(i.getName(),i.getType())).collect(Collectors.toSet());
			Table table = new Table(this.sourceId,tb.getDatabaseName()+"."+tb.getTblName(),fields);
			tables.add(table);
		}
		return tables;
	}

	@Override
	public boolean extract() {
		return false;
	}
}
