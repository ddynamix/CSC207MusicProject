const { MongoClient } = require("mongodb");

const connectionString = "mongodb+srv://<username>:<password>@cluster0.vlnfmzu.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0";
const authMechanism = "DEFAULT";

async function authorizeRequest() {
    try {
        // Create a new MongoClient instance.
        const client = new MongoClient(connectionString, {
            authMechanism: authMechanism
        });
        // Connect to the MongoDB server.
        await client.connect();
        // Authenticate with the MongoDB server.
        await client.db().command({ ping: 1 });
        console.log("Request authorized successfully.");
        // Close the connection.
        await client.close();
    } catch (error) {
        console.error("Failed to authorize request:", error);
    }
}
authorizeRequest();
