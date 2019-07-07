package scala.commands
import scala.filesystem.State
import scala.files.Directory
import scala.files.DirEntry
import scala.annotation.tailrec

class Cd(dir: String) extends Command {

  override def apply(state: State): State = {

    // cd /a/b/c
    // cd b/c
    // cd ..
    // cd .
    // cd a/./.././a/

    // 1. find root
    val root = state.root
    val wd = state.wd

    // 2. find the abs path of the directory I want to cd to
    val absolutePath = 
      if (dir.startsWith(Directory.SEPARATOR)) dir
      else if (wd.isRoot) wd.path + dir
      else wd.path + Directory.SEPARATOR + dir


    // 3. find the directory to cd to given the path
    val destinationDirectory = doFindEntry(root, absolutePath)

    // 4. change the state given the new directory
    if (destinationDirectory == null || destinationDirectory.isFile)
      state.setMessage(dir + ": no such directory")
    else 
      State(root, destinationDirectory.asDirectory)
  }

  def doFindEntry(root: Directory, path: String): DirEntry = {
    @tailrec
    def findEntryHelper(currentDirectory: Directory, tokens: List[String]): DirEntry =
      if (tokens.isEmpty || tokens.head.isEmpty) {
        println("---------------------") 
        println(" inside tokens.isEmpty OR tokens.head.isEmpty")
        println("---------------------") 
        currentDirectory
      }
      else if (tokens.tail.isEmpty) {
        println("---------------------") 
        println(" inside tokens.tail.isEmpty")
        val res = currentDirectory.findEntry(tokens.head) 
        println(res)
        println("---------------------") 
        res
      }
      else {
        println("---------------------") 
        println(" inside else")
        println("---------------------") 
        val nextDir = currentDirectory.findEntry(tokens.head)
        if (nextDir == null || nextDir.isFile) null
        else findEntryHelper(nextDir.asDirectory, tokens.tail)
      }

    // 1. tokens
    val tokens: List[String] = path.substring(1).split(Directory.SEPARATOR).toList
    // println("==============================")
    // println(tokens)
    // println(tokens.head)
    // println("==============================")


    // 2. navigate to the correct entry
    findEntryHelper(root, tokens)

  }
}