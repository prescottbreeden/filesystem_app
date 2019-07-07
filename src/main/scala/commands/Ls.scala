package scala.commands
import scala.filesystem.State
import scala.files.DirEntry

class Ls extends Command {

  override def apply(state: State): State = {
    val contents = state.wd.contents
    val niceOutput = createNiceOutput(contents)
    state.setMessage(niceOutput)
  }

  def createNiceOutput(contents: List[DirEntry]): String = {
    if (contents.isEmpty) ""
    else  {
      val entry = contents.head
      entry.name + "[" + entry.getType + "]\n" + createNiceOutput(contents.tail) 
    }
  }
}