
import com.box.sdk._
import com.typesafe.config.ConfigFactory
import collection.JavaConversions._
import java.io._

object BoxTransfer extends App {
  
  val conf = ConfigFactory.load() 
  val api = new BoxAPIConnection(args(1))
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