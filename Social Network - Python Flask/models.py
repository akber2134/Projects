import os
import sqlite3 as sql

from myenv.Scripts.activate_this import path

ROOT = os.path.dirname(os.path.relpath(__file__))
#ROOT = os.getcwd()


def create_post(name, content):
    con = sql.connect(os.path.join(ROOT, 'database.db'))
    cur = con.cursor()
    cur.execute('insert into posts(name, content) values (?, ?)', (name, content))
    con.commit()
    con.close()


def get_posts():
    con = sql.connect(ROOT + 'database.db')
    cur = con.cursor()
    cur.execute('select * from posts')
    posts = cur.fetchall()
    return posts
