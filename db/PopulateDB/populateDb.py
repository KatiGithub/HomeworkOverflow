from __future__ import print_function
import names
import psycopg2
import random
import datetime
import pytz

hostname = 'localhost'
username = 'postgres'
password = 'music_2001'
database = 'homeworkoverflow'

firstnames = []
lastnames = []

for x in range(1000):
    firstnames.append(names.get_first_name())
    lastnames.append(names.get_last_name())

def populatetblUser():

    cursor.execute("DELETE FROM tbluser;")
    cursor.execute("ALTER SEQUENCE tbluser_userid_seq RESTART WITH 1;")
    myConnection.commit()

    for index in range(1000):
            email = str(random.randint(100, 2000)) + "@test.com"
            firebaseUid = random_firebaseUid()
            insert_query = ""
            insert_query = """INSERT INTO tbluser (firstname, lastname, email, firebaseuid) VALUES('{0}', '{1}', '{2}', '{3}');""" 
            insert_query = insert_query.format(firstnames[index], lastnames[index], str(index) +'@test.com', firebaseUid)
            cursor.execute(insert_query)
    myConnection.commit()

def populatetblQuestion():
    cursor.execute("DELETE FROM tblquestion;")
    cursor.execute("ALTER SEQUENCE tblquestion_questionid_seq RESTART WITH 1;")
    myConnection.commit()

    for index in range(2000):
        questionTitle = "Title of the Question"
        questionContent = "Content of such Question"
        tags = ["math", "science", "arithmetics"]
        tagString = "ARRAY " + str(tags)
        dateAsked = datetime.datetime.now(pytz.utc)
        numberOfComments = random.randint(0, 1000)
        numberOfUpvotes = random.randint(0, 1000)

        insert_query = """INSERT INTO tblquestion(questiontitle, questioncontent, tags, dateasked, numberofcomments, numberofupvotes, askeremail)
                            VALUES('{0}', '{1}', {2}, '{3}', {4}, {5}, (SELECT email FROM tbluser ORDER BY RANDOM() LIMIT 1));"""

        insert_query = insert_query.format(questionTitle, questionContent, tagString, dateAsked, numberOfComments, numberOfUpvotes)

        cursor.execute(insert_query)
    myConnection.commit()


def populateDb():
    populatetblUser()
    populatetblQuestion()

    
def random_firebaseUid():
    range_start = 10**(10-1)
    range_end = (10**10)-1
    return random.randint(range_start, range_end)

myConnection = psycopg2.connect(
    host=hostname, user=username, password=password, database=database)
cursor = myConnection.cursor()

populateDb()

cursor.execute("SELECT * from tbluser")
userrecord = cursor.fetchall()
print(userrecord)
print("--------------------------------")


cursor.execute("SELECT * FROM tblquestion")
questionrecord = cursor.fetchall()
print(questionrecord)
print("--------------------------------")