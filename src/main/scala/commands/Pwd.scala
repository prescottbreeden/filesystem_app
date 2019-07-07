package scala.commands
import scala.filesystem.State

class Pwd extends Command {

  override def apply(state: State): State = 
    state.setMessage(state.wd.path)
}