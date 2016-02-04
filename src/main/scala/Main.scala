
import com.box.sdk._
import collection.JavaConversions._
import java.io._
import com.typesafe.config.ConfigFactory

object Main extends App {
  val conf = ConfigFactory.load() 
  val api = new BoxAPIConnection(conf.getString("box.key"))
  val rootFolder = BoxFolder.getRootFolder(api)
  
 val incoming = new BoxFolder(api, "6365824785")
 println(incoming.getInfo.getName())


/*
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
	*/
}