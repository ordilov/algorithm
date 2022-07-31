package programmers.practice

import java.util.*

//[퍼즐 조각 채우기] https://school.programmers.co.kr/learn/courses/30/lessons/84021?language=kotlin
class Solution84021 {
    private val dx = arrayOf(0, 0, 1, -1)
    private val dy = arrayOf(1, -1, 0, 0)

    fun solution(game_board: Array<IntArray>, table: Array<IntArray>): Int {
        val visitedTable = Array(table.size) { BooleanArray(table[0].size) }
        val visitedBoard = Array(game_board.size) { BooleanArray(game_board[0].size) }
        val gameBoard = game_board.map { row -> row.map { it != 1 }.toBooleanArray() }
        val tableBoard = table.map { row -> row.map { it != 0 }.toBooleanArray() }
        val blanks: MutableList<Array<Position>> = mutableListOf()
        val blocks: MutableList<Array<Position>> = mutableListOf()

        for (i in game_board.indices) {
            for (j in game_board[0].indices) {
                if (gameBoard[i][j] && !visitedBoard[i][j]) {
                    blanks.add(bfs(i, j, visitedBoard, gameBoard))
                }
                if (tableBoard[i][j] && !visitedTable[i][j]) {
                    blocks.add(bfs(i, j, visitedTable, tableBoard))
                }
            }
        }
        blanks.forEach { it.sort() }
        blocks.forEach { it.sort() }

        return matchBlock(blanks, blocks)
    }

    private fun matchBlock(
        blanks: List<Array<Position>>,
        blocks: List<Array<Position>>
    ): Int {
        var size = 0
        val visited = BooleanArray(blanks.size)
        for (block in blocks) {
            for (j in blanks.indices) {
                val blank = blanks[j]
                if (blank.size != block.size || visited[j] || !isMatched(block, blank)) continue
                visited[j] = true
                size += block.size
                break
            }
        }
        return size
    }

    private fun isMatched(block: Array<Position>, location: Array<Position>): Boolean {
        var rotatedBlock = block
        for (i in 0 until 4) {
            rotatedBlock = rotate(rotatedBlock)
            if (location.contentEquals(rotatedBlock)) return true
        }
        return false
    }

    private fun rotate(block: Array<Position>): Array<Position> {
        var minX: Int
        var minY: Int
        return block.map { Position(it.y, -it.x) }
            .sorted()
            .also { minX = it[0].x; minY = it[0].y }
            .map { Position(it.x - minX, it.y - minY) }
            .toTypedArray()
    }

    private fun bfs(
        x: Int,
        y: Int,
        visited: Array<BooleanArray>,
        checkList: List<BooleanArray>,
    ): Array<Position> {
        val queue = LinkedList<Position>().apply { add(Position(x, y)) }
        val list = mutableListOf<Position>().apply { add(Position(0, 0)) }
        visited[x][y] = true

        while (queue.isNotEmpty()) {
            val (cx, cy) = queue.poll()
            for (i in 0 until 4) {
                val nx = cx + dx[i]
                val ny = cy + dy[i]
                if (nx < 0 || nx >= checkList.size || ny < 0 || ny >= checkList[0].size) continue
                if (!checkList[nx][ny] || visited[nx][ny]) continue
                visited[nx][ny] = true
                queue.add(Position(nx, ny))
                list.add(Position(nx - x, ny - y))
            }
        }
        return list.toTypedArray()
    }

    data class Position(val x: Int, val y: Int) : Comparable<Position> {
        override fun compareTo(other: Position): Int {
            return if (this.x == other.x) {
                this.y - other.y
            } else {
                this.x - other.x
            }
        }
    }
}

fun main() {
    println(
        Solution84021().solution(
            arrayOf(
                intArrayOf(1, 1, 0, 0, 1, 0),
                intArrayOf(0, 0, 1, 0, 1, 0),
                intArrayOf(0, 1, 1, 0, 0, 1),
                intArrayOf(1, 1, 0, 1, 1, 1),
                intArrayOf(1, 0, 0, 0, 1, 0),
                intArrayOf(0, 1, 1, 1, 0, 0)
            ), arrayOf(
                intArrayOf(1, 0, 0, 1, 1, 0),
                intArrayOf(1, 0, 1, 0, 1, 0),
                intArrayOf(0, 1, 1, 0, 1, 1),
                intArrayOf(0, 0, 1, 0, 0, 0),
                intArrayOf(1, 1, 0, 1, 1, 0),
                intArrayOf(0, 1, 0, 0, 0, 0)
            )
        )
    )
    println(
        Solution84021().solution(
            arrayOf(
                intArrayOf(0, 0, 0),
                intArrayOf(1, 1, 0),
                intArrayOf(1, 1, 1)
            ), arrayOf(
                intArrayOf(1, 1, 1),
                intArrayOf(1, 0, 0),
                intArrayOf(0, 0, 0)
            )
        )
    )
}