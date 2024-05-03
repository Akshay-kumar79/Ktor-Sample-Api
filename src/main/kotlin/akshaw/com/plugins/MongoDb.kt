package akshaw.com.plugins

import akshaw.com.mappers.toExposedUser
import akshaw.com.mappers.toUser
import akshaw.com.models.Movie
import akshaw.com.models.User
import com.mongodb.client.model.Filters.eq
import com.mongodb.kotlin.client.coroutine.MongoClient
import com.mongodb.kotlin.client.coroutine.MongoCollection
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId


class MongoDb {

    private val password = "JvjGrCi5aCrwW5fz"
    private val connectionString =
        "mongodb+srv://akshay8459361606:$password@cluster0.wwgrnl1.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"

    private val mongoDbClient = MongoClient.create(connectionString)
    private val database = mongoDbClient.getDatabase("user_database")
    private val collection: MongoCollection<User> = database.getCollection("users")

    suspend fun create(user: ExposedUser): String? {
        return try {
            val result = collection.insertOne(user.toUser())
            result.insertedId?.toString()
        } catch (e: Exception) {
            null
        }
    }

    suspend fun read(id: ObjectId): ExposedUser? {
        return try {
            val result = collection.find(eq("_id", id)).firstOrNull()
            result?.toExposedUser()
        } catch (e: Exception) {
            null
        }
    }

}