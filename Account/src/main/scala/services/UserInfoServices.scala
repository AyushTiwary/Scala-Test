package services

import module.UserInfo
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable
import scala.concurrent.Future

class UserInfoServices {

  val accessToken: String = "abcd"

  private var userData = mutable.Map(9560790485L -> UserInfo("Ayush", "qwerty", 9560790485L))

  def addUser(user: UserInfo): Future[Boolean] = Future {
    if (userData.contains(user.contactNo)) {
      false
    }
    else {
      userData += (user.contactNo -> user)
      true
    }
  }

  def Authenticate(userName: String, password: String) = Future {

    val checkValues = for {(mobileNumber, user) <- userData
                           boolResult = if (user.name.equals(userName) && user.password.equals(password)) {
                             true
                           } else false
    } yield (mobileNumber, boolResult)

    if (checkValues.toList.map(_._2) contains (true)) {
      accessToken
    }
    else {
      s"Sorry Username or password doesn't match"
    }
  }
}