from fastapi import FastAPI
from pymongo import MongoClient
from pymongo.errors import ServerSelectionTimeoutError

app = FastAPI()
client = MongoClient("mongodb://mongodb:27017/")
db = client.counter_db

@app.on_event("startup")
def startup_db_client():
    db.counter_collection.insert_one({"counter": 0})

@app.get("/")
async def read_root():
    try:
        counter = db.counter_collection.find_one()
        new_count = counter['counter'] + 1
        db.counter_collection.update_one({'_id': counter['_id']}, {"$set": {"counter": new_count}})
        return {"Hello": "FastAPI", "Visits": new_count}
    except ServerSelectionTimeoutError:
        return {"message": "Database connection failed. Counter disabled."}