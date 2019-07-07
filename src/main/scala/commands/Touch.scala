package scala.commands
import scala.files.DirEntry
import scala.filesystem.State
import scala.files.Directory
import scala.files.File

class Touch(name: String) extends CreateEntry(name: String) {
  override def createSpecificEntry(state: State): DirEntry = {
    File.empty(state.wd.path, name)
  }
}