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

def cleartables():
    cursor.execute("DELETE FROM tblupvote;")
    cursor.execute("DELETE FROM tblanswer;")
    cursor.execute("DELETE FROM tblquestion;")
    cursor.execute("DELETE FROM tbluser WHERE email != 'kati.pairojtanachai@gmail.com';")

def populatetblUser():
    cursor.execute("ALTER SEQUENCE tbluser_userid_seq RESTART WITH 1;")
    myConnection.commit()

    for index in range(1000):
            email = str(random.randint(100, 2000)) + "@test.com"
            firebaseUid = random_firebaseUid()
            insert_query = ""
            insert_query = """INSERT INTO tbluser (firstname, lastname, email, firebaseuid, title) VALUES('{0}', '{1}', '{2}', '{3}', '{4}');""" 
            insert_query = insert_query.format(firstnames[index], lastnames[index], str(index) +'@test.com', firebaseUid, 'Frontend Dev')
            cursor.execute(insert_query)
    myConnection.commit()

def populatetblQuestion():
    cursor.execute("ALTER SEQUENCE tblquestion_questionid_seq RESTART WITH 1;")
    myConnection.commit()

    for index in range(2000):
        questionTitle = "Title of the Question" + str(index + 1)
        questionContent = "Content of such Question"
        tags = ["math", "science", "arithmetics"]
        tagString = "ARRAY " + str(tags)
        dateAsked = datetime.datetime.now(pytz.utc)
        numberOfComments = random.randint(0, 1000)
        numberOfUpvotes = random.randint(0, 1000)

        insert_query = """INSERT INTO tblquestion(questiontitle, questioncontent, tags, dateasked, askeruserid)
                            VALUES('{0}', '{1}', {2}, '{3}', (SELECT userid FROM tbluser ORDER BY RANDOM() LIMIT 1));"""

        insert_query = insert_query.format(questionTitle, questionContent, tagString, dateAsked, numberOfComments, numberOfUpvotes)

        cursor.execute(insert_query)
    myConnection.commit()

def populatetblanswer():
    cursor.execute("ALTER SEQUENCE tblanswer_answerid_seq RESTART WITH 1;")
    myConnection.commit()

    for index in range(2000):
        answercontent = "Content of such answer"
        dateAnswered = datetime.datetime.now(pytz.utc)
        numberOfComments = random.randint(0, 1000)
        numberOfUpvotes = 1

        insert_query = """INSERT INTO tblanswer(questionid, answeruserid, answercontent, dateposted,upvotes)
                            VALUES(
                                (SELECT questionid FROM tblquestion ORDER BY RANDOM() LIMIT 1), 
                                (SELECT userid FROM tbluser ORDER BY RANDOM() LIMIT 1),
                                '{0}',
                                '{1}',
                                {2}
                            );"""
        insert_query = insert_query.format(answercontent, dateAnswered, numberOfUpvotes)

        cursor.execute(insert_query)
    myConnection.commit()

def populatetblupvote():
    myConnection.commit()

    for index in range(1999):
        insert_query = """INSERT INTO tblupvote(answerid, userid) VALUES({0}, {1});"""
        insert_query = insert_query.format(index + 1, 1001)

        cursor.execute(insert_query)
    myConnection.commit()


def populateDb():
    cleartables()
    populatetblUser()
    populatetblQuestion()
    populatetblanswer()
    populatetblupvote()

    
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

cursor.execute("SELECT * FROM tblanswer")
questionrecord = cursor.fetchall()
print(questionrecord)
print("--------------------------------")

cursor.execute("SELECT * FROM tblupvote")
questionrecord = cursor.fetchall()
print(questionrecord)
print("--------------------------------")