package OperationChain

/**
  * Created by LeoChen on 2016/1/17.
  */
object OperationChain {

  case class Video(title: String, video_type: String, length: Int)
  case class User(name: String, id: String)
  case class Movie(name: String, id: String)

  def catTime(videos: Vector[Video]): Int ={
    videos.filter(video => video.video_type == "cat").map(video => video.length).sum
  }

  def getUserById(id: String) = id match {
    case "1" => Some(User("Mike", "1"))
    case _ => None
  }
  def getFavoriteMovieForUser(user: User) = user match {
    case User(_, "1") => Some(Movie("Gigli", "101"))
    case _ => None
  }
  def getVideosForMovie(movie: Movie) = movie match {
    case Movie(_, "101") =>
      Some(Vector(
        Video("Interview With Cast", "interview", 480),
        Video("Gigli", "feature", 7260)))
    case _ => None
  }

  def getFavoriteVideos(userId: String) =
    for {
      user <- getUserById(userId)
      favoriteMovie <- getFavoriteMovieForUser(user)
      favoriteVideos <- getVideosForMovie(favoriteMovie)
    } yield favoriteVideos

  val v1 = Video("Pianocat Plays Carnegie Hall", "cat", 300)

  val v2 = Video("Paint Drying", "home-improvement", 600)

  val v3 = Video("Fuzzy McMittens Live At The Apollo", "cat", 200)

  val videos = Vector(v1, v2, v3)

  def main(args: Array[String]) {
    println(catTime(videos))

    println(getFavoriteVideos("1").getOrElse("None"))
  }
}