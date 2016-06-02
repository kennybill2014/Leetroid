Question:
Given a binary tree, determine if it is height-balanced.
For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differs by more than 1.

public boolean isBalanced(TreeNode root) {
	return maxDepth(root) != -1;
}

private int maxDepth(TreeNode root) {
	if (root == null) return 0;
	int L = maxDepth(root.left);
	if (L == -1) return -1;
	int R = maxDepth(root.right);
	if (R == -1) return -1;
	return (Math.abs(L - R) <= 1) ? (Math.max(L, R) + 1) : -1;
}
