package com.filmster.androidTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.filmster.test.model.IMedia;
import com.filmster.test.repository.FilmsterRepository;

import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class FilmsterRepositoryTest {

    private FilmsterRepository repo;
    private IMedia movie;

    /*
    @Before
    public void getAppContext() {
        // Context of the app under test.
        //Default test

        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        LifecycleOwner owner = new LifecycleOwner() {
            @NonNull
            @Override
            public Lifecycle getLifecycle() {
                return null;
            }
        };

        repo = FilmsterRepository.getInstance(appContext);
        repo.loadMedias();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        repo.getCurrentMedia().observe(owner, new Observer<IMedia>() {
            @Override
            public void onChanged(IMedia iMedia) {
                movie = iMedia;
            }
        });
    }

    @Test
    public void getCurrentMovieTest() {
        // testing which the current movie is

        System.out.println(repo.getCurrentMedia().getValue().getTitle() + " title");
        assertEquals(  "Inception",movie.getTitle());
    }


     */
}
