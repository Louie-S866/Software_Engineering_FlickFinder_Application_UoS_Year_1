# COM1028 - Software Engineering

# Assignment 1 - Report

**Student Name: Louie Sherman**

**Student ID: 6858075**

## 1.1 Proto Personas

### Persona 1

 **Monty, a film critic**
Monty, age 23, is a new and upcoming film critic in central London, a common place of work for several film critics companies such as Film Critics Association UK and Film Critics Limited.  Monty grew up in a small town called Hitchin, where his parents both work as teachers.  His father teaches Design & Technology whilst his mother teaches chemistry.  Monty has a bachelors of arts in Film Studies and has just started his new job at the same company he worked at for his placement year where he was offered a job for after he finished his degree.
 
Monty is extremely enthusiastic when it comes to films.  He believes that with the assistance of digital technologies and data, film reviews and articles are becoming easier to write and a good website/application to get reliable statistics/information is important to ensure good integrity.  After some research he has found a new mobile application called FlickFinder which has particularly interested him. This is because FlickFinder will give him reliable data and has the option to search for specific films and actors which makes it easy to use and simple to understand.

### Persona 2

**Martin, a Film Director**
Martin, aged 49 is a Film Director that works in New York.  He has been directing for 25 years and started right after achieving a masters in Film and Media Studies at university.  He moved to New York from Chicago due to the rising film industry competition.  Martin usually struggles to find good ideas for new films and usually finds his best ideas/plots when researching and watching films for himself.  He believes that digital technologies to find trends are the way forward in finding new ideas/plots, however he doesn't want to rely on them fully and still wants to view existing film's and actor's past data himself so he can see which ideas/plots have the best reviews and generate the most income.  Martin recently discovered a new mobile application which suits his needs called FlickFinder.  FlickFinder allows efficient lookup of films, actors and ratings, all displayed in a simplistic way which is easy to read and understand.

## 1.2 Scenario

**Film critic in London**

Monty's only experience in the film & media industry is from his placement year at university (King's College London) where he was working as a film critic.  Monty is extremely enthusiastic and has exceeded the manager's expectations whilst working there despite his limited experience in the media sector.  Whilst working there during his placement he realised he spent the majority of his time searching for information on the Internet for his article (mainly using Wikipedia).  This encouraged him to ask colleagues and search for a new application to make gathering information (specifically film related information) easier and quicker whilst also being accurate and reliable (as he knows that some people/websites tend to bend the truth when writing articles and reviews).  After some searching and asking around one of his colleagues showed him FlickFinder, a mobile film search application.

The reason why FlickFinder appealed to Monty so much is because it used an IMDB database, Monty had used IMDB before in his articles and knew it was a trusted source of information.  FlickFinder has all the essential data related to each film and actor (for example each film has a year of release and a title).  Furthermore FlickFinder allows you to search for specific films and actors in a simple and easy way and displays the results individually on a page which is straightforward to understand.  This is perfect for Monty as it allows him to find data quickly and efficiently which is far better than scouring the Internet for reliable data.  However, Monty needs a lot of data for his research so he can produce honest and high quality articles which FlickFinder sometimes lacks.  For example FlickFinder uses a free IMDB database which has limited data such as budgets (which the data might not be available) and is missing essential data such as net profits.  Overall making Monty go back to searching the Internet for more specific, niche answers.

Even though FlickFinder does appeal to Monty, he was also shown websites such as Letterboxd, IMDb and Rotten Tomatoes which are already established and big companies / websites which have trusted data which Monty can use.  However some of these websites (Rotten Tomatoes and Letterboxd) primarily focus on the reviews from critics and the general public instead of the raw data about films and they don't mention any actors apart from in reviews.  Thus FlickFinder has the advantage over the competition due to the fact that it is simple to use (compared to IMDb) and has all of the raw data Monty needs to complete his articles.  Due to this Monty chose to use FlickFinder as a primary source of information and used other websites to find any niche, specific data which FlickFinder cannot/doesn't provide.


## 1.3 User Stories

### User Story 1

**Film critic**

As a film critic, I need a way to find the best and worst ratings for a specific film so that I can properly judge what the film does right and what is most entertaining to people / what people enjoy about the film the most.

### User Story 2

**Film researcher**

As a film researcher, I need to be able to look at the top hundred films (and descriptions) which generated the most income so that I can see which movie genres and plots are most profitable.

### User Story 3

**Line producer**

As a line producer, I need to be able to find the actors with the highest rated films and their salaries so that I can create a rough staff/actor budget.

### User Story 4

**Casting director**

As a casting director, I need a way to find the top 500 actors with the highest combined ratings for all films they've starred in so that I can cast/hire the best actors for the film I am working on.

### User Story 5

**Actor**

As an actor, I want to find the top 20 directors with the highest rated films so that I can reach out to them to find any new opportunities.

### User Story 6

**Line producer**

As a line producer, I need to be able to find the films will the highest net profit and budget so that I can create a realistic budget for the film I am working on. 


## 2 Critical Analysis and Reflection

### 2.1 Reflection

All of the sections work as intended and all tests pass including the more detailed integrated tests.  The controller and dao communicate properly and there are no errors.  All methods in all classes are commented and are easy to understand.  This application could be improved by adding increased complexity by testing more extreme values and implementing the directors table in the IMDB database to give the user more information to deal with.  Additionally a search bar and a table with everyone's IDs could be implemented to firstly make finding data a lot easier (since altering the URL can be tedious), and secondly a table with everyone's IDs will be useful since the user doesn't know which IDs relate to which actor/film without looking at the actual database.  Thus quality of life features could be added to make the application more user-friendly.

### 2.2 Professional Aspects

Regarding legal and ethical issues, FlickFinder should try to use the most recent version of the IMDB database to ensure data is up to date.  As for the license for the IMDB database, it is an open source and free database therefore we do not need to worry about any copyright infringements.  FlickFinder doesn't take any personal information but should we want to make FlickFinder application a subscription service to earn money from it we will have to handle user's private information.  This means that we will have to protect user's private information which is stated by law in the Data Protection Act 2018.  Furthermore we will have to abide by IMDB's terms and conditions regarding the reselling of their information since we are using a free IMDB database for non-commerical use (Information from: *How much does using IMDb cost?*, no date).  Additionally we will have to establish a Code of Conduct and terms & conditions to make evident our obligations and expectations.

Regarding maintainability we need to ensure that our code is both easy to read and understand (ensure we have a good and up-to-date report).  This is so if someone else wants to evolve FlickFinder in the future it is easier to update the code rather than re-writing the application from scratch.  Due to FlickFinder being a fairly simple application in terms of scale, refactoring is cheaper, less complex and is necessary to ensure the application stays manageable.

FlickFinder makes use of the MVC system architecture, this is beneficial because we can present the same data in different ways and alter data independently of its representation.  However, one drawback to MVC is that it can involve additional code (can increase code complexity) when the data model and interactions are simple.  Overall for the user it is easy to view the data which makes FlickFinder very user-friendly, although due to FlickFinder not being very complex, it suffers from complex code thus it may be more beneficial to use a different architecture such as a client-server architecture instead.


## 3. References

*How much does using IMDb cost?*: (no date) Available at:
https://help.imdb.com/article/imdb/general-information/how-much-does-using-imdb-cost/G47U365PP8GQVQYV?ref_=helpart_nav_31# (Accessed 1st May 2025)
