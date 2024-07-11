const {MongoClient} = require('mongodb');

async function main() {

    const uri = "mongodb+srv://tasnimreza:<dbtemppassword>@atlascluster.tijckuy.mongodb.net/?retry" +
        "Writes=true&w=majority&appName=AtlasCluster"


    const client = new MongoClient(uri);

        try {
            await client.connect();
            await listDatabases(client);
        } catch (e) {
            console.error(e);
        } finally {
            await client.close();
        }
}

main().catch(console.error);

async function listDatabases(client){
    let databasesList = await client.db().admin().listDatabases();

    console.log("Databases:");
    databasesList.databases.forEach(db => console.log(` - ${db.name}`));
}
