package scala.files
import scala.filesystem.FilesystemException

class File(override val parentPath: String, override val name: String, contents: String)
  extends DirEntry(parentPath, name) {

    def asDirectory: Directory = 
      throw new FilesystemException("File cannot be converted to a directory")

    def asFile: File = this

    def getType: String = "File"

    def isDirectory: Boolean = false

    def isFile: Boolean = true
}

object File {

  def empty(parentPath: String, name: String): File = {
    new File(parentPath, name, "")
  }
}