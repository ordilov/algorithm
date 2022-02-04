package boj.tree

//[트리의 순회] https://www.acmicpc.net/problem/2263
fun main() {
    val n = readLine()!!.toInt()
    val inorder = readLine()!!.split(" ").map { it.toInt() }
    val postorder = readLine()!!.split(" ").map { it.toInt() }

    connect(inorder, postorder)?.print()
}

private fun connect(inorder: List<Int>, postorder: List<Int>): Node? {
    if (inorder.isEmpty() || postorder.isEmpty()) return null

    val root = postorder.last()
    val index = inorder.indexOf(root)
    val now = Node(root)

    now.left = connect(inorder.subList(0, index), postorder.subList(0, index))
    now.right = connect(
        inorder.subList(index + 1, inorder.size),
        postorder.subList(index, postorder.size - 1)
    )

    return now
}

private class Node(val value: Int, var left: Node? = null, var right: Node? = null)

private fun Node.print() {
    print("${this.value} ")
    left?.print()
    right?.print()
}