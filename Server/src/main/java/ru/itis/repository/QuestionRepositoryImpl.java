package ru.itis.repository;

import ru.itis.models.Question;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuestionRepositoryImpl {
    private final Set<Integer> notAvailableIdSet = new HashSet<>();
    private final List<Question> questionArray = initQuestionArray();
    //private final Random randomGenerator = new Random(questionArray.size());

    public Question getQuestion() {
        int newPosition = generate();
        while (!notAvailableIdSet.add(newPosition)) {
            newPosition = generate();
        }
        return questionArray.get(newPosition);
    }

    private int generate() {
        return (int) (Math.random() * 18);
    }

    private List<Question> initQuestionArray() {
        int defaultPoints = 10;
        return Arrays.asList(Question
                        .builder()
                        .question("What’s the difference between == and === operators in Kotlin?")
                        .answers(new String[]{
                                "They are same",
                                "First compares only by reference, second compares like an equals",
                                "First works like an equals, second compares only by references",
                                "No difference"})
                        .correctAnsId(2)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("What’s Collections in Java?")
                        .answers(new String[]{"Library",
                                "Framework",
                                "Plugin",
                                "Class"})
                        .correctAnsId(2)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("What is marked with an annotation @Bean (Spring Framework)")
                        .answers(new String[]{"Class", "Variable", "Fabric method", "All answers incorrect"})
                        .correctAnsId(2)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("List down the visibility modifiers available in Kotlin. What’s the default visibility modifier?")
                        .answers(new String[]{
                                "public(default), private, protected",
                                "public(default), private, internal, protected",
                                "package private(default), private, public, protected",
                                "private(default), package private, internal, protected, public"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("What are the data class methods?")
                        .answers(new String[]{
                                "copy, equals, hashcode, toString",
                                "equals, hashcode, toString", "equals, hashcode",
                                "copy, equals, hashcode, toString, notify, notifyAll"})
                        .correctAnsId(0)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("What is the worst complexity of the add() method in ArrayList?")
                        .answers(new String[]{"O(n^2)", "O(1)", "O(logn)", "O(n)"})
                        .correctAnsId(3)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("In which version of Java was the try-with-resources operator added? :)(:")
                        .answers(new String[]{"Java 8", "Java 7", "Java 5", "Java 11"})
                        .correctAnsId(0)
                        .points(defaultPoints)
                        .build(),
                Question
                        .builder()
                        .question("Which keyword is not used to work with exceptions?")
                        .answers(new String[]{"finally", "throws", "case", "catch"})
                        .correctAnsId(2)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("What annotation is used when redefining a method?").
                        answers(new String[]{"@Redefine", "@Override", "Override", "Redefine"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("What is the maximum value of hashCode()?").
                        answers(new String[]{"2^32", " 2^16", " 2^8", " 2^4"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Implementation of PagerAdapter that uses a Fragment to manage each page," +
                                " this class also handles saving and restoring of fragment's state. ").
                        answers(new String[]{
                                "FragmentStatePagerAdapter",
                                "FragmentStateAdapter",
                                "FragmentPagerAdapter",
                                "PagerAdapterImpl"})
                        .correctAnsId(0)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Which of the above is not a component of android").
                        answers(new String[]{
                                "Activity",
                                "Fragment",
                                "BroadcastReceiver",
                                "ContentProvider"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Which of the following is true about WorkManager").
                        answers(new String[]{
                                "Tasks are typically chained, but not run in parallel",
                                "WorkManager is part of Android Jetpack",
                                "WorkManager doesn't necessarily guarantee that a task will be executed",
                                "WorkRequest is responsible for scheduling and running tasks"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Making execution dependent on device state such as storage space and battery life are examples of").
                        answers(new String[]{"chaining", "best practices", "constraints", "canceling tasks"})
                        .correctAnsId(2)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Which of the following would not require WorkManager?").
                        answers(new String[]{"Performing a GET request to a web service",
                                "Long running tasks such as downloading large amounts of data",
                                "Scheduling a task to repeat after a set interval",
                                "Doing something while the app is in the background"})
                        .correctAnsId(0)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Which statement below is true about coroutines?").
                        answers(new String[]{
                                "Once started, a coroutine cannot be canceled",
                                "A coroutine always runs on the main thread",
                                "A coroutine may or may not execute",
                                "Coroutines run every task on the same thread"})
                        .correctAnsId(2)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("What is responsible for determining which thread is used behind the scenes by a coroutine?").
                        answers(new String[]{
                                "CoroutineScope",
                                "Dispatcher",
                                "Job",
                                "GlobalScope"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Which order of clauses in a SELECT statement is correct?").
                        answers(new String[]{
                                "WHERE, LIMIT, ORDER BY, GROUP BY",
                                "WHERE, LIMIT, GROUP BY, ORDER BY",
                                "WHERE, ORDER BY, GROUP BY, LIMIT",
                                "WHERE, GROUP BY, ORDER BY, LIMIT"})
                        .correctAnsId(3)
                        .points(defaultPoints)
                        .build(),
                Question.builder()
                        .question("Which statement below is false about async() and runBlocking()?").
                        answers(new String[]{"Both functions take a CoroutineScope (a suspend function) as a parameter",
                                "Both functions return a Deferred",
                                "You'll typically not use runBlocking in Android app code",
                                "When using async, you need to use await() to access the returned value"})
                        .correctAnsId(1)
                        .points(defaultPoints)
                        .build());
    }
}
