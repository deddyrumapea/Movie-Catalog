package com.romnan.moviecatalog.util

import com.romnan.moviecatalog.R
import com.romnan.moviecatalog.model.Movie
import com.romnan.moviecatalog.model.TvShow

object DataDummy {
    fun generateDummyMovies(): List<Movie> {
        val movies = ArrayList<Movie>()
        movies.add(
            Movie(
                "332562-a-star-is-born",
                R.drawable.poster_a_star_is_born,
                "A Star Is Born (2018)",
                "https://www.youtube.com/watch?v=nSbzyEJ8X9E",
                "10/05/2018",
                "Drama, Romance, Music",
                "2h 16m",
                75,
                "",
                "Seasoned musician Jackson Maine discovers — and falls in love with — struggling artist Ally. She has just about given up on her dream to make it big as a singer — until Jack coaxes her into the spotlight. But even as Ally's career takes off, the personal side of their relationship is breaking down, as Jack fights an ongoing battle with his own internal demons.",
                "Bradley Cooper",
                "Bradley Cooper, Lady Gaga, Sam Elliott",
                "Released",
                "English",
                "$36,000,000.00",
                "$433,888,866.00",
                "country music, waitress, self-destruction, talent, pop star, concert, addiction, alcoholism, remake, death of father, aspiring singer, singer, fame, tinnitus, falling in love, insecurity, alcoholic, death of mother, aspiration, death of parent, showbiz, emotional vulnerability, hearing impaired, brother brother relationship"
            )
        )

        movies.add(
            Movie(
                "399579-alita-battle-angel",
                R.drawable.poster_alita,
                "Alita: Battle Angel (2019)",
                "https://www.youtube.com/watch?v=w7pYhpJaJW8",
                "02/14/2019",
                "Action, Science Fiction, Adventure",
                "2h 2m",
                71,
                "An angel falls. A warrior rises.",
                "When Alita awakens with no memory of who she is in a future world she does not recognize, she is taken in by Ido, a compassionate doctor who realizes that somewhere in this abandoned cyborg shell is the heart and soul of a young woman with an extraordinary past.",
                "Robert Rodriguez",
                "Rosa Salazar, Christoph Waltz, Jennifer Connelly",
                "Released",
                "English",
                "170,000,000.00",
                "404,852,543.00",
                "martial arts, bounty hunter, extreme sports, dystopia, superhero, cyberpunk, based on manga, female cyborg, live action remake, floating city, manga"
            )
        )

        movies.add(
            Movie(
                "297802-aquaman",
                R.drawable.poster_aquaman,
                "Aquaman (2018)",
                "https://www.youtube.com/watch?v=WDkg3h8PCVU",
                "12/21/2018",
                "Action, Adventure, Fantasy",
                "2h 24m",
                69,
                "Home Is Calling",
                "Once home to the most advanced civilization on Earth, Atlantis is now an underwater kingdom ruled by the power-hungry King Orm. With a vast army at his disposal, Orm plans to conquer the remaining oceanic people and then the surface world. Standing in his way is Arthur Curry, Orm's half-human, half-Atlantean brother and true heir to the throne.",
                "James Wan",
                "Jason Momoa, Amber Heard, Willem Dafoe",
                "Released",
                "English",
                "$160,000,000.00",
                "$1,148,461,807.00",
                "dc comics, hero, atlantis, half-brother, superhero, based on comic, royalty, shark, duringcreditsstinger, dc extended universe"
            )
        )

        movies.add(
            Movie(
                "424694-bohemian-rhapsody",
                R.drawable.poster_bohemian,
                "Bohemian Rhapsody (2018)",
                "https://www.youtube.com/watch?v=mP0VHJYFOAU",
                "11/02/2018",
                "Drama, Music",
                "2h 15m",
                80,
                "Fearless lives forever",
                "Singer Freddie Mercury, guitarist Brian May, drummer Roger Taylor and bass guitarist John Deacon take the music world by storm when they form the rock 'n' roll band Queen in 1970. Hit songs become instant classics. When Mercury's increasingly wild lifestyle starts to spiral out of control, Queen soon faces its greatest challenge yet – finding a way to keep the band together amid the success and excess.",
                "Bryan Singer",
                "Rami Malek, Gwilym Lee Ben Hardy",
                "Released",
                "English",
                "$52,000,000.00",
                "$894,027,543.00",
                "london, england, aids, 1970s, queen, musician, biography, based on a true story, hiv, male homosexuality, singer, fame, rock band, music band, lgbt, 1980s, parsi, gay, 2018 "
            )
        )

        movies.add(
            Movie(
                "438650-cold-pursuit ",
                R.drawable.poster_cold_pursuit,
                "Cold Pursuit (2019) ",
                "https://www.youtube.com/watch?v=0phuNQQ_gHI",
                "02/08/2019",
                "Action, Crime, Thriller ",
                "1h 59m ",
                56,
                "Meet Nels Coxman. Citizen of the Year.",
                "The quiet family life of Nels Coxman, a snowplow driver, is upended after his son's murder. Nels begins a vengeful hunt for Viking, the drug lord he holds responsible for the killing, eliminating Viking's associates one by one. As Nels draws closer to Viking, his actions bring even more unexpected and violent consequences, as he proves that revenge is all in the execution.",
                "Hans Petter Moland",
                "Liam Neeson Laura Dern Emmy Rossum",
                "Released",
                "English",
                "$60,000,000.00",
                "$59,213,931.00",
                "colorado, drug dealer, revenge, dark comedy, gun battle, snow"
            )
        )

        movies.add(
            Movie(
                "480530-creed-ii",
                R.drawable.poster_creed,
                "Creed II (2018)",
                "https://www.youtube.com/watch?v=cPNVNqn4T9I",
                "11/21/2018",
                "Drama",
                "2h 10m",
                69,
                "There's More to Lose than a Title",
                "Between personal obligations and training for his next big fight against an opponent with ties to his family's past, Adonis Creed is up against the challenge of his life.",
                "Steven Caple Jr.",
                "Michael B. Jordan Sylvester Stallone Dolph Lundgren ",
                "Released ",
                "English ",
                "$50,000,000.00",
                "$214,215,889.00",
                "sports, sequel, los angeles, california, boxing, box ring, ukraine"
            )
        )

        movies.add(
            Movie(
                "338952-fantastic-beasts-the-crimes-of-grindelwald ",
                R.drawable.poster_crimes,
                "Fantastic Beasts: The Crimes of Grindelwald (2018)",
                "https://www.youtube.com/watch?v=5sEaYB4rLFQ",
                "11/16/2018",
                "Adventure, Fantasy, Drama",
                "2h 14m",
                69,
                "Fate of One. Future of All.",
                "Gellert Grindelwald has escaped imprisonment and has begun gathering followers to his cause—elevating wizards above all non-magical beings. The only one capable of putting a stop to him is the wizard he once called his closest friend, Albus Dumbledore. However, Dumbledore will need to seek help from the wizard who had thwarted Grindelwald once before, his former student Newt Scamander, who agrees to help, unaware of the dangers that lie ahead. Lines are drawn as love and loyalty are tested, even among the truest friends and family, in an increasingly divided wizarding world.",
                "David Yates",
                "Eddie Redmayne Katherine Waterston Alison Sudol ",
                "Released",
                "English ",
                "$200,000,000.00",
                "$653,355,901.00",
                "paris, france, new york city, witch, world war ii, sequel, old flame, wizard, 1920s, harry potter, wizarding world"
            )
        )

        movies.add(
            Movie(
                "450465-glass",
                R.drawable.poster_glass,
                "Glass (2019)",
                "https://www.youtube.com/watch?v=95ghQs5AmNk",
                "01/18/2019",
                "Thriller, Drama, Science Fiction",
                "2h 9m",
                66,
                "You Cannot Contain What You Are",
                "In a series of escalating encounters, former security guard David Dunn uses his supernatural abilities to track Kevin Wendell Crumb, a disturbed man who has twenty-four personalities. Meanwhile, the shadowy presence of Elijah Price emerges as an orchestrator who holds secrets critical to both men.",
                "M. Night Shyamalan",
                "James McAvoy  Bruce Willis  Anya Taylor-Joy",
                "Released",
                "English",
                "$20,000,000.00",
                "$246,941,965.00",
                "villain, sequel, superhero, psychiatric hospital, comic book shop, violence, super power, mental illness, multiple personality, dissociative identity disorder, surveillance specialist, eastrail 177 universe, eastrail 177, eastrail 177 trilogy "
            )
        )

        movies.add(
            Movie(
                "166428-how-to-train-your-dragon-3",
                R.drawable.poster_how_to_train,
                "How to Train Your Dragon: The Hidden World (2019)",
                "https://www.youtube.com/watch?v=qNGLuCijKZ0",
                "02/22/2019",
                "Animation, Family, Adventure",
                "1h 44m",
                78,
                "The friendship of a lifetime",
                "As Hiccup fulfills his dream of creating a peaceful dragon utopia, Toothless’ discovery of an untamed, elusive mate draws the Night Fury away. When danger mounts at home and Hiccup’s reign as village chief is tested, both dragon and rider must make impossible decisions to save their kind.",
                "Dean DeBlois",
                "Jay Baruchel America Ferrera F. Murray Abraham",
                "Released",
                "English",
                "$129,000,000.00",
                "$517,526,875.00",
                "flying, based on novel or book, overpopulation, viking, finale, sequel, computer-generated imagery, dragon, love interest, based on children's book"
            )
        )

        movies.add(
            Movie(
                "299536-avengers-infinity-war",
                R.drawable.poster_infinity_war,
                "Avengers: Infinity War (2018)",
                "https://www.youtube.com/watch?v=6ZfuNTqbHE8",
                "04/27/2018",
                "Adventure, Action, Science Fiction",
                "2h 29m",
                83,
                "An entire universe. Once and for all.",
                "As the Avengers and their allies have continued to protect the world from threats too large for any one hero to handle, a new danger has emerged from the cosmic shadows: Thanos. A despot of intergalactic infamy, his goal is to collect all six Infinity Stones, artifacts of unimaginable power, and use them to inflict his twisted will on all of reality. Everything the Avengers have fought for has led up to this moment - the fate of Earth and existence itself has never been more uncertain.",
                "Joe Russo",
                "Robert Downey Jr. Chris Hemsworth Chris Evans Scarlett Johansson",
                "Released",
                "English",
                "$300,000,000.00",
                "$2,046,239,637.00",
                "magic, sacrifice, superhero, based on comic, space, battlefield, genocide, magical object, super power, team, aftercreditsstinger, marvel cinematic universe, cosmic"
            )
        )

        return movies
    }

    fun generateDummyTvShows(): ArrayList<TvShow> {
        val tvShows = ArrayList<TvShow>()

        tvShows.add(
            TvShow(
                "1412-arrow",
                R.drawable.poster_arrow,
                "Arrow (2012)",
                "https://www.youtube.com/watch?v=hTv13EjlLNg",
                "Crime, Drama, Mystery, Action & Adventure",
                "42m",
                65,
                "Heroes fall. Legends rise.",
                "Spoiled billionaire playboy Oliver Queen is missing and presumed dead when his yacht is lost at sea. He returns five years later a changed man, determined to clean up the city as a hooded vigilante armed with a bow.",
                "Greg Berlanti",
                "Stephen Amell, David Ramsey, Katie Cassidy",
                "Ended",
                "Scripted",
                "English",
                "dc comics, superhero, based on comic, superhero, team, masked, vigilante",
                "Season 8 2019 | 10 Episodes\nIn Arrow's final season, Oliver Queen is forced to confront the reality of what it means to be a hero."
            )
        )

        tvShows.add(
            TvShow(
                "79501-doom-patrol",
                R.drawable.poster_doom_patrol,
                "Doom Patrol (2019)",
                "https://www.youtube.com/watch?v=6tTM9nbRk5A",
                "Sci-Fi & Fantasy, Action & Adventure",
                "49m",
                75,
                "",
                "The Doom Patrol’s members each suffered horrible accidents that gave them superhuman abilities — but also left them scarred and disfigured. Traumatized and downtrodden, the team found purpose through The Chief, who brought them together to investigate the weirdest phenomena in existence — and to protect Earth from what they find.",
                "Jeremy Carver",
                "Diane Guerrero, April Bowlby, Joivan Wade",
                "Returning Series",
                "Scripted",
                "English",
                "dc comics,based on comic,misfit,superhero team,weird science",
                "Season 2 2020 | 9 Episodes\nThe members of the Doom Patrol now find themselves mini-sized and stranded on Cliff’s toy race car track. Here they begin to deal with their feelings of betrayal by Niles Caulder aka The Chief, while confronting their own personal baggage. And as each member faces the challenge of growing beyond their own past traumatic experiences, they must come together to embrace and protect the newest member of the family: Dorothy Spinner, Niles’ daughter, whose powers remain a mysterious but real threat to bringing on the end of the world."
            )
        )

        tvShows.add(
            TvShow(
                "12609-dragon-ball",
                R.drawable.poster_dragon_ball,
                "Dragon Ball (1986)",
                "https://www.youtube.com/watch?v=y_0APzy4BcU",
                "Comedy, Sci-Fi & Fantasy, Animation, Action & Adventure",
                "25m",
                80,
                "",
                "Long ago in the mountains, a fighting master known as Gohan discovered a strange boy whom he named Goku. Gohan raised him and trained Goku in martial arts until he died. The young and very strong boy was on his own, but easily managed. Then one day, Goku met a teenage girl named Bulma, whose search for the mystical Dragon Balls brought her to Goku's home. Together, they set off to find all seven and to grant her wish.",
                "Akira Toriyama",
                "Masako NozawaMayumi TanakaHiromi Tsuru",
                "Ended",
                "Scripted",
                "Japanese",
                "martial arts,kung fu,monster,flying,competition,karate,rivalry,alien,anthropomorphism,tournament,dragon,based on manga,fighting,combat,super power,shounen,anime",
                "Season 1 1986 | 153 Episodes\nSeason 1 of Dragon Ball premiered on February 26, 1986."
            )
        )

        tvShows.add(
            TvShow(
                "1434-family-guy",
                R.drawable.poster_family_guy,
                "Family Guy (1999)",
                "https://youtu.be/t3VtKdoPIYE",
                "Animation, Comedy",
                "22m",
                68,
                "Parental Discretion Advised, that's how you know it's good",
                "Sick, twisted, politically incorrect and Freakin' Sweet animated series featuring the adventures of the dysfunctional Griffin family. Bumbling Peter and long-suffering Lois have three kids. Stewie (a brilliant but sadistic baby bent on killing his mother and taking over the world), Meg (the oldest, and is the most unpopular girl in town) and Chris (the middle kid, he's not very bright but has a passion for movies). The final member of the family is Brian - a talking dog and much more than a pet, he keeps Stewie in check whilst sipping Martinis and sorting through his own life issues.",
                "Seth MacFarlane",
                "Seth MacFarlane, Alex Borstein, Mila Kunis",
                "Returning Series",
                "Scripted",
                "English",
                "lifestyle,laughing,adult humor,satire,parody,middle class,dysfunctional family,swear word,child swearing,social satire,language,nasty neighbor,boy genius,adult animation,swearing,humor,neighbors",
                "Season 19 2020 | 4 Episodes\nSeason 19 of Family Guy premiered on September 27, 2020."
            )
        )

        tvShows.add(
            TvShow(
                "60735-the-flash",
                R.drawable.poster_flash,
                "The Flash (2014)",
                "https://www.youtube.com/watch?v=Yj0l7iGKh8g",
                "Drama, Sci-Fi & Fantasy",
                "44m",
                75,
                "The fastest man alive.",
                "After a particle accelerator causes a freak storm, CSI Investigator Barry Allen is struck by lightning and falls into a coma. Months later he awakens with the power of super speed, granting him the ability to move through Central City like an unseen guardian angel. Though initially excited by his newfound powers, Barry is shocked to discover he is not the only meta -human who was created in the wake of the accelerator explosion -- and not everyone is using their new powers for good. Barry partners with S.T.A.R. Labs and dedicates his life to protect the innocent. For now, only a few close friends and associates know that Barry is literally the fastest man alive, but it won't be long before the world learns what Barry Allen has become...The Flash.",
                "Greg Berlanti",
                "Grant Gustin, Candice Patton, Carlos Valdes",
                "Returning Series",
                "Scripted",
                "English",
                "dc comics,superhero,speed,based on comic,super power,masked superhero,supervillain",
                "Season 6 2019 | 19 Episodes\nWhen Barry and Iris deal with loss of their daughter, the team faces their greatest threat yet - one that threatens to destroy all of Central City; Killer Frost has a brush with death that will change her relationship with Caitlin. Faced with the news of his impending death, Barry's resiliency suffers as he struggles to fight fate."
            )
        )

        tvShows.add(
            TvShow(
                "60708-gotham",
                R.drawable.poster_gotham,
                "Gotham (2014)",
                "https://youtu.be/0d1zpt6k5OI",
                "Drama, Fantasy, Crime",
                "43m",
                74,
                "Before Batman, there was Gotham.",
                "Everyone knows the name Commissioner Gordon. He is one of the crime world's greatest foes, a man whose reputation is synonymous with law and order. But what is known of Gordon's story and his rise from rookie detective to Police Commissioner? What did it take to navigate the multiple layers of corruption that secretly ruled Gotham City, the spawning ground of the world's most iconic villains? And what circumstances created them – the larger-than-life personas who would become Catwoman, The Penguin, The Riddler, Two-Face and The Joker?",
                "Bruno Heller",
                "Ben McKenzie, Donal Logue, David Mazouz",
                "Ended",
                "Scripted",
                "English",
                "dc comics,insane asylum,based on comic,super power,super villain,origin story",
                "Season 5 2019 | 12 Episodes\nFollowing the epic events of last season, the Legend of the Dark Knight resumes as Gotham City is divided between Gordon’s GCPD jurisdiction and some of the city’s most notorious villains. As the city’s heroes try to gain control and salvage what’s left of the deteriorating city, it teeters between good and evil, even as new villains, including the iconic Bane are introduced."
            )
        )

        tvShows.add(
            TvShow(
                "1416-grey-s-anatomy",
                R.drawable.poster_grey_anatomy,
                "Grey's Anatomy (2005)",
                "https://www.youtube.com/watch?v=qbkEtYz9acA",
                "Drama",
                "43m",
                80,
                "The life you save may be your own.",
                "Follows the personal and professional lives of a group of doctors at Seattle’s Grey Sloan Memorial Hospital.",
                "Shonda Rhimes",
                "Ellen Pompeo, Justin Chambers, James Pickens Jr.",
                "Returning Series",
                "Scripted",
                "English",
                "seattle,trauma,workplace,hospital,doctor,medical student,surgical interns,medical drama",
                "Season 17 2020 | 2 Episodes\nSeason 17 of Grey's Anatomy is set to premiere on November 12, 2020."
            )
        )

        tvShows.add(
            TvShow(
                "54155-hanna",
                R.drawable.poster_hanna,
                "Hanna (2019)",
                "https://www.youtube.com/watch?v=oNYp4i5N_s4",
                "Action & Adventure, Drama",
                "50m",
                74,
                "",
                "This thriller and coming-of-age drama follows the journey of an extraordinary young girl as she evades the relentless pursuit of an off-book CIA agent and tries to unearth the truth behind who she is. Based on the 2011 Joe Wright film.",
                "David Farr",
                "Esme Creed-Miles, Mireille Enos, Noah Taylor",
                "Returning Series",
                "Scripted",
                "English",
                "underage soldier,coming of age,thriller,based on movie,child soldier,vengeance",
                "Season 2 2020 | 8 Episodes\nIn Season 2, Hanna is drawn out of hiding when her sole friend is recaptured by Utrax. With the help of Marissa Wiegler, Hanna infiltrates the strange new Utrax facility called ‘The Meadows’ to rescue Clara."
            )
        )

        tvShows.add(
            TvShow(
                "62127-marvel-s-iron-fist",
                R.drawable.poster_iron_fist,
                "Marvel's Iron Fist (2017)",
                "https://www.youtube.com/watch?v=f9OKL5no-S0",
                "Action & Adventure, Drama, Sci-Fi & Fantasy,",
                "55m",
                64,
                "",
                "Danny Rand resurfaces 15 years after being presumed dead. Now, with the power of the Iron Fist, he seeks to reclaim his past and fulfill his destiny.",
                "Scott Buck",
                "Finn Jones, Jessica Henwick, Jessica Stroup",
                "Canceled",
                "Scripted",
                "English",
                "martial arts,superhero,based on comic,super power,marvel cinematic universe",
                "Season 2 2018 | 10 Episodes\nSeason 2 furthers the transformation of Danny​, a character with a fish out of water coming of age story making his way in a harsh new world, battling to work out who he is. This season, Dann​y​ has promised that with Matt Murdock gone, he will step up and protect his city. ​But a sinister plot twist threatens his very identity ​and he must conquer his villains to protect the town and people he holds close to his heart."
            )
        )

        tvShows.add(
            TvShow(
                "31910-naruto-shipp-den",
                R.drawable.poster_naruto_shipudden,
                "Naruto Shippūden (2007)",
                "https://www.youtube.com/watch?v=1WLO0Owi5-A",
                "Animation, Comedy, Drama",
                "25m",
                87,
                "",
                "Naruto Shippuuden is the continuation of the original animated TV series Naruto.The story revolves around an older and slightly more matured Uzumaki Naruto and his quest to save his friend Uchiha Sasuke from the grips of the snake-like Shinobi, Orochimaru. After 2 and a half years Naruto finally returns to his village of Konoha, and sets about putting his ambitions to work, though it will not be easy, as He has amassed a few (more dangerous) enemies, in the likes of the shinobi organization; Akatsuki.",
                "Kazuhiko Inoue",
                "Kazuhiko Inoue, Jouji Nakata, Akira Ishida",
                "Ended",
                "Scripted",
                "Japanese",
                "hero,fight,villain,sequel,ninja,teenager,based on manga,martial arts master,martial artist,teenage boy,teenager fighting adult,teenage hero,ninjitsu,ninja warrior,shounen,anime",
                "Infinite Tsukuyomi: The Invocation 2015 | 87 Episodes\nThe season focuses on Naruto Uzumaki, Sasuke Uchiha, Sakura Haruno and Kakashi Hatake attempting to defeat Madara Uchiha and Zetsu, the ones behind the activation of the Infinite Tsukuyomi. There is also a side story revolving around Itachi Uchiha's life in the Leaf Village and his early days in the Akatsuki."
            )
        )

        tvShows.add(
            TvShow(
                "1402-the-walking-dead",
                R.drawable.poster_the_walking_dead,
                "The Walking Dead (2010)",
                "https://www.youtube.com/watch?v=R1v0uFms68U",
                "Action & Adventure, Drama, Sci-Fi & Fantasy",
                "42m",
                79,
                "Fight the dead. Fear the living.",
                "Sheriff's deputy Rick Grimes awakens from a coma to find a post-apocalyptic world dominated by flesh-eating zombies. He sets out to find his family and encounters many other survivors along the way.",
                "Frank Darabont",
                "Norman Reedus, Danai Gurira, Andrew Lincoln",
                "Returning Series",
                "Scripted",
                "English",
                "post-apocalyptic future, gore, survivor, undead, based on comic, horror, survivalzombie",
                "Season 10 2019 | 22 Episodes\nIt is now Spring, a few months after the end of Season 9, when our group of survivors dared to cross into Whisperer territory during the harsh winter. The collected communities are still dealing with the after effects of Alpha’s horrific display of power, reluctantly respecting the new borderlines being imposed on them, all while organising themselves into a militia-style fighting force, preparing for a battle that may be unavoidable. But the Whisperers are a threat unlike any they have ever faced. Backed by a massive horde of the dead it is seemingly a fight they cannot win."
            )
        )

        return tvShows
    }
}