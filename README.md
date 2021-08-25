#  API Definition:
/analyse/new
##API Input:
This API endpoint should take at least two RSS URLs as a parameter (more are possible) e.g.:

https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss

#input param:
["https://news.google.com/news?cf=all&hl=en&pz=1&ned=us&output=rss", "https://news.yahoo.com/rss/us"]

#API Response:
For each request executed against the API endpoint you should return an unique identifier, which will be the input for the second API endpoint.

# Workflow:
When the the API is being called, your code should do a HTTP request to fetch the RSS feeds. Your code should then analyse the entries in this feed and find potential hot topics --> are there any overlaps between the news.

Example:
RSS Feed one contains following news: To Democrats, Donald Trump Is No Longer a Laughing Matter Burundi military sites attacked, 12 insurgents killed San Bernardino divers return to lake seeking electronic evidence

RSS Feed two contains following news: Attacks on Military Camps in Burundi Kill Eight Saudi Women to Vote for First Time Platini Dealt Further Blow in FIFA Presidency Bid

Your analysis should return that there are news related to Burundi in both feeds. The analysed data should be stored within a data store and referenced by an unique identifier (see API response).

# API Definition:
/frequency/{id}
##API Input:
This API endpoint takes an id as input and call the GET 

##API Output:
Returns the three elements with the most matches, additinally the orignal news header and the link to the whole news text should be displayed.

##example 
[
    {
        "keyWord": "man",
        "news": [
            {
                "title": "Police: Passed-out man had 300 mail-in California recall ballots | TheHill - The Hill",
                "link": "https://news.google.com/__i/rss/rd/articles/CBMib2h0dHBzOi8vdGhlaGlsbC5jb20vaG9tZW5ld3Mvc3RhdGUtd2F0Y2gvNTY5MzM3LXBvbGljZS1wYXNzZWQtb3V0LW1hbi1oYWQtMzAwLW1haWwtaW4tY2FsaWZvcm5pYS1yZWNhbGwtYmFsbG90c9IBc2h0dHBzOi8vdGhlaGlsbC5jb20vaG9tZW5ld3Mvc3RhdGUtd2F0Y2gvNTY5MzM3LXBvbGljZS1wYXNzZWQtb3V0LW1hbi1oYWQtMzAwLW1haWwtaW4tY2FsaWZvcm5pYS1yZWNhbGwtYmFsbG90cz9hbXA?oc=5"
            },
            {
                "title": "Minneapolis man faces 11 charges in armed burglary, assault and carjacking spree in St. Paul",
                "link": "https://news.yahoo.com/minneapolis-man-faces-11-charges-220500785.html"
            },
            {
                "title": "‘I’m not resisting’: Video shows police officer beating Black man with flashlight",
                "link": "https://news.yahoo.com/m-not-resisting-video-shows-213239273.html"
            },
            {
                "title": "Bloomfield man avoids prison in robbery of marijuana dealer in Windsor",
                "link": "https://news.yahoo.com/bloomfield-man-avoids-prison-robbery-200800313.html"
            },
            {
                "title": "Nashville man charged with civil rights violations after several alleged church arsons",
                "link": "https://news.yahoo.com/nashville-man-charged-civil-rights-193700122.html"
            }
        ]
    },
    {
        "keyWord": "assault",
        "news": [
            {
                "title": "Ron Jeremy indicted on 30 counts of sexual assault in L.A. - Los Angeles Times",
                "link": "https://news.google.com/__i/rss/rd/articles/CBMidWh0dHBzOi8vd3d3LmxhdGltZXMuY29tL2NhbGlmb3JuaWEvc3RvcnkvMjAyMS0wOC0yNS9yb24tamVyZW15LWluZGljdGVkLW9uLTMwLWNvdW50cy1vZi1zZXh1YWwtYXNzYXVsdC1pbi1sb3MtYW5nZWxlc9IBAA?oc=5"
            },
            {
                "title": "Minneapolis man faces 11 charges in armed burglary, assault and carjacking spree in St. Paul",
                "link": "https://news.yahoo.com/minneapolis-man-faces-11-charges-220500785.html"
            },
            {
                "title": "Sheriff's deputies accused of covering up on-duty assault in East L.A.",
                "link": "https://news.yahoo.com/sheriffs-deputies-accused-covering-duty-210950846.html"
            },
            {
                "title": "Porn star Ron Jeremy indicted on more than 30 sexual assault counts involving 21 victims dating back more than two decades",
                "link": "https://news.yahoo.com/porn-star-ron-jeremy-indicted-210200705.html"
            },
            {
                "title": "Ron Jeremy indicted on more than 30 counts of sexual assault in Los Angeles",
                "link": "https://news.yahoo.com/ron-jeremy-indicted-30-counts-202208483.html"
            }
        ]
    },
    {
        "keyWord": "court",
        "news": [
            {
                "title": "GUILTY: Man pardoned by Bevin convicted of murder in federal court",
                "link": "https://news.yahoo.com/guilty-man-pardoned-bevin-convicted-221600980.html"
            },
            {
                "title": "Appeals court rejects case accusing NYU Law Review of bias",
                "link": "https://news.yahoo.com/appeals-court-rejects-case-accusing-212339680.html"
            },
            {
                "title": "Political operative at center of NC elections scandal hospitalized before court date",
                "link": "https://news.yahoo.com/political-operative-center-nc-elections-211757350.html"
            },
            {
                "title": "Appeals court upholds death sentence for Charleston church shooter Dylann Roof",
                "link": "https://news.yahoo.com/appeals-court-upholds-death-sentence-210000573.html"
            },
            {
                "title": "Baltimore police are not liable for $166,000 judgment against ex-cop, state’s highest court rules",
                "link": "https://news.yahoo.com/baltimore-police-not-liable-166-200300517.html"
            }
        ]
    }
]

#Workflow:
When this API is being called, you will read the analysis data stored in the database by using the given id parameter Return the top three results as a json object ordered by their frequency of occurrence

##Stack
Spring JPA H2 database running in memory (data will not be persistent across application restarts) You are free to add / change any libraries which you might need to solve this exercise, the only requirement is that we do not have to setup / install any external software to run this application.

Running the exercise with maven

##mvn spring-boot:run