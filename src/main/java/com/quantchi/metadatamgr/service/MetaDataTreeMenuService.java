package com.quantchi.metadatamgr.service;

import com.quantchi.common.JsonResult;
import com.quantchi.common.TreeBuilder;
import com.quantchi.common.TreeNode;
import com.quantchi.metadatamgr.data.entity.MDMountNodeInfo;
import com.quantchi.metadatamgr.data.entity.MdRuletypeNodeInfo;
import com.quantchi.metadatamgr.data.mapper.MDMountNodeInfoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Service
public class MetaDataTreeMenuService {

	private static final Logger logger = LoggerFactory.getLogger(MetaDataTreeMenuService.class);

	@Autowired
	private MDMountNodeInfoMapper mdMountNodeInfoMapper;


	private static String DATASOURCETYPE = "datasource";
	private static String SYSTEMTYPE = "system";

	public List<MDMountNodeInfo> getMenuTree(){
		List<MDMountNodeInfo> mountNodes =  mdMountNodeInfoMapper.getMountNodeInfo();
		return (List<MDMountNodeInfo>) TreeBuilder.buildListToTree(mountNodes);
	}

	public List<MDMountNodeInfo> searchMenuTree(String keyword){
		List<MDMountNodeInfo> allMountNodes =  mdMountNodeInfoMapper.getMountNodeInfo();
		allMountNodes = (List<MDMountNodeInfo>) TreeBuilder.buildListToTree(allMountNodes);
		List<MDMountNodeInfo> mountNodes = mdMountNodeInfoMapper.searchMountNodeInfo(keyword);
		List<MDMountNodeInfo> mountNodesTables = mdMountNodeInfoMapper.searchMountNodeInfoByParentId(mountNodes.stream().filter(i-> "field".equals(i.getType())).map(i->i.getParentId()).collect(Collectors.toList()));
		mountNodes.addAll(mountNodesTables);
		mountNodes = (List<MDMountNodeInfo>) TreeBuilder.buildListToTree(mountNodes);
		TreeBuilder.joinTreeAndRemoveOnlyDsLeaf(allMountNodes,mountNodes);
		removeEmptyLeafNode(allMountNodes,keyword);
		return allMountNodes;
	}

	public List<MDMountNodeInfo> getTablesInMenuTree(String datasourceId){
		return  mdMountNodeInfoMapper.getTableMountNodeInfo(datasourceId);
	}

	public List<MDMountNodeInfo> getFieldsInMenuTree(String tableId){
		return  mdMountNodeInfoMapper.getFieldsMountNodeInfo(tableId);
	}

	private void removeEmptyLeafNode(List<? extends TreeNode> mountNodeInfos,String keyword){
		Iterator<TreeNode> iter = (Iterator<TreeNode>) mountNodeInfos.iterator();
		while (iter.hasNext()){
			TreeNode node = iter.next();
			if(node.getChildren().size() > 0)
				removeEmptyLeafNode(node.getChildren(),keyword);

			if(node.getChildren().size() > 0 || keyword.equals(node.getName()))
				continue;

			if(((MDMountNodeInfo)node).getType().equals(DATASOURCETYPE)
							||((MDMountNodeInfo)node).getType().equals(SYSTEMTYPE)){
				iter.remove();
			}
		}
	}

  public List<Map<String,Object>> getBusinSystemList (Map<String,Object> map)  {
  	return  mdMountNodeInfoMapper.getBusinSystemList(map);
  }

  public List<MdRuletypeNodeInfo> getRuleTypeTree(){
		List<MdRuletypeNodeInfo> mountNodes =  mdMountNodeInfoMapper.getRuleTypeNodeInfo();
		return (List<MdRuletypeNodeInfo>) TreeBuilder.buildListToTree(mountNodes);
	}
	public String  getRuleType(String type){
		List<MdRuletypeNodeInfo> mountNodes =  mdMountNodeInfoMapper.getRuleTypeNodeInfo();
		List<MdRuletypeNodeInfo> ruleMapList = new ArrayList<MdRuletypeNodeInfo>();
		if (type.equals("big")) {
        for (MdRuletypeNodeInfo mdRuletypeNodeInfo : mountNodes){
        	 if ( mdRuletypeNodeInfo.getParentId() == null || mdRuletypeNodeInfo.getParentId().isEmpty() ){
				ruleMapList.add(mdRuletypeNodeInfo);
			}
		}
		}else
		{ for (MdRuletypeNodeInfo mdRuletypeNodeInfo : mountNodes){
			if ( mdRuletypeNodeInfo.getParentId() != null && (!mdRuletypeNodeInfo.getParentId().isEmpty()))
			{
				ruleMapList.add(mdRuletypeNodeInfo);
			}
		}
		}
		return JsonResult.successJson(ruleMapList);
	}
}
