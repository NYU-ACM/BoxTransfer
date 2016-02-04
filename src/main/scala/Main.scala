
import com.box.sdk._
import com.typesafe.config.ConfigFactory
import collection.JavaConversions._
import java.io._

object Main extends App {
  
  val conf = ConfigFactory.load() 
  val api = new BoxAPIConnection(conf.getString("box.key"))
 	val incoming = new BoxFolder(api, conf.getString("box.incoming"))
 	val source = new File(args(0))
  val boxRoot = incoming.createFolder(source.getName)

	iterateFS(source, new BoxFolder(api, boxRoot.getID))

	def iterateFS(root: File, boxFolder: BoxFolder) {
	  root.listFiles.foreach{ obj =>
	    obj.isFile match {
	    	case true => { boxFolder.uploadFile(new FileInputStream(obj), obj.getName) }
	    	case false => { iterateFS(obj, new BoxFolder(api, boxFolder.createFolder(obj.getName).getID)) } 
	    }
	  }	
	}

}