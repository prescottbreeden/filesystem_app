package scala.commands
import scala.filesystem.State
import scala.files.Directory
import scala.files.DirEntry

class Mkdir(name: String) extends CreateEntry(name: String) {

  override def createSpecificEntry(state: State): DirEntry = {
    Directory.empty(state.wd.path, name)
  }
}