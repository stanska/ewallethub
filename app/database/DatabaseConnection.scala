package database

import com.mongodb.casbah.MongoClient

object DatabaseConnection {
  var client: MongoClient = null
  def setMongoClient(newClient: MongoClient) = { client = newClient }
  def getMongoClient = client
}