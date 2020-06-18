package com.ravitej.awesomemovies.datamapper;

public interface DataMapper<Input, Output> {

    Output convert(Input input);
}
