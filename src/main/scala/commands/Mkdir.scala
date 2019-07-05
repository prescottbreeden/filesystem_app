package scala.commands
import scala.filesystem.State
import scala.files.Directory
import scala.files.DirEntry

class Mkdir(name: String) extends Command {

  override def apply(state: State): State = {
    val wd = state.wd
    if (wd.hasEntry(name)) {
      state.setMessage(s"Entry $name already exists!")
    } else if (name.contains(Directory.SEPARATOR)) {
      state.setMessage(s"$name must not contain separators.")
    } else if (checkIllegal(name)) {
      state.setMessage(s"$name: illegal entry name.")
    } else {
      doMkdir(state, name)
    }
  } 

  def checkIllegal(name: String): Boolean = {
    name.contains(".")
  }

  def doMkdir(state: State, name: String): State = {
    def updateStructure(
      currentDirectory: Directory, 
      path: List[String], 
      newEntry: DirEntry): Directory = ???

    val wd = state.wd

    // 1. all the directories in the full path
    val allDirsInPath: List[String] = wd.getAllFoldersInPath
    
    // 2. create new directory entry in the wd
    val newDir = Directory.empty(wd.path, name) 

    // 3. update the whole directory structure starting from the root
    val newRoot = updateStructure(state.root, allDirsInPath, newDir)

    // 4. find new working directory INSTANCE given wd's full path in the new 
    //    directory structure (Directory structure is IMMUTABLE)
    val newWd = newRoot.findDescendant(allDirsInPath)

    State(newRoot, newWd)

  }

}