package com.example.lotbot;

import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController // Or @RestController, which one? Why?
public class LotBotController {
    // Here we're storing a list of lots.
    // does a list make sense here? It may,
    // but it's also possible another data
    // structure will make your life easier.
    // think critically about your choices
    List<Lot> lots = new ArrayList<>();
    List<Transaction> transactions = new ArrayList<>();


    /**
     * The @PostConstruct method will cause whichever
     * method it's annotating to run after the controller
     * is created by Spring.
     * <p>
     * In this case, let's use it to give a value to
     * our list of "lots"
     */
    @PostConstruct
    public void postConstruct() {
        // we have to create lots of lots here
        // if you want fewer lots than 20
        // that's okay too
        for (int i = 0; i < 20; i++) {
            lots.add(Lot.createLot());
        }
    }

    @GetMapping("/lots")
    public List<Lot> displayLots() {
        return lots;
    }

    @GetMapping("/lots/{id}")
    public Lot displayLot(@PathVariable int id) {
        return lots.get(id);
    }

    @PostMapping("/lots/{id}/{spot}")
    public void parkCar(@PathVariable int id, @RequestBody Car car, @PathVariable int spot) {
        Transaction transaction = new Transaction();
        transaction.setCar(car);
        transaction.setCheckedInDate(LocalDateTime.now());
        lots.get(id).getSpaces()[spot] = new Space(transaction);

        //must return car object in the request body
    }

    @PutMapping("/lots/{id}/{spot}")
    public double leaveSpot(@PathVariable int id, @RequestBody Car car, @PathVariable int spot) {
        Transaction t = lots.get(id).getSpaces()[spot].getTransaction();
        t.setCheckedOutDate(LocalDateTime.now());
        double time = DateHelper.getHoursBetweenDates(t.getCheckedInDate(), t.getCheckedOutDate());
        t.setPrice(time * 11);
        lots.get(id).getSpaces()[spot].setTransaction(null);
        //return lots.get(id).getSpaces();
        return t.getPrice();
        //1. set the checked out date in the spaces transaction
        //2. set the price (hours spent parking * rate)
        //3. remove the transaction from the space. like space.setTransaction(null);

    }

    @GetMapping("/transactions")
    public List<Transaction> allReceipts() {
        return transactions;
    }
}


