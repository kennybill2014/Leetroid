Question:
Given a binary tree, determine if it is a valid Binary Search Tree (BST).

private TreeNode prev;
public boolean isValidBST(TreeNode root) {
	prev = null;
	return isMonotonicIncreasing(root);
}
private boolean isMonotonicIncreasing(TreeNode p) {
	if (p == null) return true;
	if (isMonotonicIncreasing(p.left)) {
		if (prev != null && p.val <= prev.val) return false;
		prev = p;
		return isMonotonicIncreasing(p.right);
	}
	return false;
}
