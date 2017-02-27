package com.company.section6_student_library_problem;

import java.util.Random;

public class Student implements Runnable {

    private int id;

    private Book[] books;

    private int attempt = 0;

    public Student(int id, Book[] books) {
        this.id = id;
        this.books = books;
    }

    @Override
    public void run() {
        Random random = new Random();
        while (true) {
            int bookId = random.nextInt(Constants.NUMBER_OF_BOOKS);
            try {
                books[bookId].read(this);
                attempt++;
                if (attempt > Constants.ATTEMPT_NUM_MAX) {
                    break;
                }
            } catch (InterruptedException iex) {
                iex.printStackTrace();
                throw new RuntimeException(iex);
            }
        }
    }

    @Override
    public String toString() {
        return "Students #" + id;
    }

}
