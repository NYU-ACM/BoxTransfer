
import com.box.sdk._
import com.typesafe.config.ConfigFactory
import collection.JavaConversions._
import java.io._

object Main extends App {
  val conf = ConfigFactory.load() 
  val api = new BoxAPIConnection(conf.getString("box.key"))
  
 	val incoming = new BoxFolder(api, "6365824785")
 	
 	val source = new File(args(0))

  val boxRoot = incoming.createFolder(source.getName)

	iterateFS(source, new BoxFolder(api, boxRoot.getID))

	def iterateFS(root: File, boxFolder: BoxFolder) {
	  root.listFiles.foreach{ obj =>
	    obj.isFile match {
	    	case true => { boxFolder.uploadFile(new FileInputStream(obj), obj.getName) }
	    	case false => { 
	    		containsChild(obj.getName, boxFolder) match {
	    			case true => //this shouldn't happen
	    			case false => {
	    				val child = boxFolder.createFolder(obj.getName)
	    				iterateFS(obj, new BoxFolder(api, child.getID))
	    			}
	    		}
	    	} 
	    }
	  }	
	}

	def containsChild(name: String, root: BoxFolder): Boolean = {
	  val children = root.getChildren()
	  var result = false

	  children.foreach { obj =>
	  	if(result == false) {
		  	obj.getName.equals(name) match {
		  		case true => result = true
		  		case false => result = false
		  	}
	  	}
	  }

	  result
	}

}