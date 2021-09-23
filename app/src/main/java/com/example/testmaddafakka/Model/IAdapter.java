package com.example.testmaddafakka.Model;

import java.util.List;

public interface IAdapter {

    public List<Movie> get250Movies();

    public List<Movie> getList(String listID);

}
