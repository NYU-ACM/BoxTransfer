
import com.box.sdk._
import collection.JavaConversions._
import java.io._

object Main extends App {
  val api = new BoxAPIConnection("JyFhPSACkMHvXuWujdJ6bJBvdgBtGfIF")
  val rootFolder = BoxFolder.getRootFolder(api);
  //iterate(rootFolder)
 	
 	val incoming = new BoxFolder(api, "6365824785")
 	println(incoming.getInfo.getName())
/*  
  val stream = new FileInputStream("testbox.txt");
  rootFolder.uploadFile(stream, "ACM/testbox.txt")
  stream.close()
*/

	def iterate(root: BoxFolder) {
	  root.getChildren().foreach{ obj => 
	  	val resource = obj.getResource
	  	resource match {
	  		case _: BoxFile => println("  ITEM: " + obj.getName + " " + obj.getID)
	  		case _: BoxFolder => { 
	  			println("FOLDER: " + obj.getName + " " + obj.getID) 
	  			iterate(resource.asInstanceOf[BoxFolder])
	  		}
	  		case _ =>
	  	}
	  }
	}
}