# tiw_web
Welcome everyone. This is a simple e-commerce based on similar aplications like vinted or wallapop. Where you can sign-up, log-in, upload items, buy items, remove items and confirm your buy in shopping cart items.

This is my first full-stack, and as you will see front end is based on EJS due tu the fact that is a simple, intuitive and easy language to start this path (if it's not the easiest, it is probably top 3 easiest languages to start in this fullStack path) and back end is based on Java.

## Instalation
So, what do you need to test this program?
1. Let's start buy downloading an IDE, I use Eclipse IDE: https://www.eclipse.org/downloads/ where you can import the downloaded project, or pull directly from this repository. However, you can use other IDE like STS or your most familiar.
2. For this full stack development, I used mySql Workbench following the next schema:
   <img src="" alt="DB Schema"/>.
3. I'm using GlashFish 5 server, so remember to download and add it to Eclipse IDE servers.
4. Then start your GlashFish 5 server where you are going to add a JDBC Resource and a JDBC Connection Pool. Firstly, add the JDBC Connection Pool:
    - Resource Type: javax.sql.DataSource.
    - Datasource Classname: com.mysql.cj.jdbc.MysqlDataSource.
   Go to properties tab and set.
   
   |Prop  |value   |
   |:-----:|:------:|
   |user|root (this is the user you set for MySql Workbench)|
   |password| root (the password you placed to access MySql Workbench)|
   |useSSL|false|
   |serverTimezone|UCT (programming from EURO zone!)|
   |url|jdbc:mysql://localhost/**comercio** (the last value is the Schema name)|
 5. Lastly, go to **Data Source Exporer**, right click and click on new. Select MySql in Connection Profile, in general set the prop from above step (Database = **comercio**, user, password, url: jdbc:mysql://localhost:3306/**comercio**) and in optional add two props: serverTimezone=UCT, useSSL=false.

## Run
Right click on the project you imported and select run -> run on server -> GlassFish 5!

That's all! Feel free to make the changes you consider or adapt it to your web!
