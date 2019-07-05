import java.util.Scanner
import scala.filesystem
import scala.files.Directory
import scala.filesystem.State
import scala.commands.Command

object FileSystem extends App {

  val root = Directory.ROOT
  var state = State(root, root)
  val scanner = new Scanner(System.in)

  while(true) {
    state.show
    val input = scanner.nextLine()
    state = Command.from(input).apply(state)
  }

}
