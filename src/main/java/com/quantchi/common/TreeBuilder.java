package com.quantchi.common;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class TreeBuilder {
	public static List<? extends TreeNode> buildListToTree(List<? extends TreeNode> dirs) {
		List<TreeNode> roots = findRoots(dirs);
		List<TreeNode> notRoots = (List<TreeNode>) CollectionUtils.subtract(dirs, roots);
		for (TreeNode root : roots) {
			//root.setChildren(findChildren(root, notRoots));
			root.getChildren().addAll(findChildren(root, notRoots));
		}
		return roots;
	}

	private static List<TreeNode> findRoots(List<? extends TreeNode> allNodes) {
		List<TreeNode> results = new ArrayList<>();
		Set<String> parentIdSet = allNodes.stream().map(i->i.getId()).collect(Collectors.toSet());
		for (TreeNode node : allNodes) {
			if (!parentIdSet.contains(node.getParentId())) {
				results.add(node);
			}
		}
		return results;
	}

	private static List<TreeNode> findChildren(TreeNode root, List<TreeNode> allNodes) {
		List<TreeNode> children = new ArrayList<>();

		for (TreeNode node : allNodes) {
			if(node.getParentId().equals(root.getId())){
				children.add(node);
			}
		}
		List<TreeNode> notChildren = (List<TreeNode>) CollectionUtils.subtract(allNodes, children);
		for (TreeNode child : children) {
			List<TreeNode> tmpChildren = findChildren(child, notChildren);
			//child.setChildren(tmpChildren);
			child.getChildren().addAll(tmpChildren);
		}
		return children;
	}

	public static void joinTreeAndRemoveOnlyDsLeaf(List<? extends TreeNode> parentNodes, List<? extends TreeNode> childNodes) {

		for (TreeNode node : parentNodes) {
			if( node.getChildren().size() > 0){
				joinTreeAndRemoveOnlyDsLeaf(node.getChildren(),childNodes);
			}else{
				for(TreeNode subNode:childNodes){
					if(subNode.getParentId().equals(node.getId())){
						node.getChildren().add(subNode);
						//don't why add break
						//break;
					}
				}
			}
			//remove no child node
			/*if(node.getChildren().size() > 0){
				Iterator<TreeNode> it = node.getChildren().iterator();
				while (it.hasNext()){
					TreeNode childNode = it.next();
					if(childNode.getChildren().size() == 0)
						it.remove();
				}
			}*/
		}
	}


}
