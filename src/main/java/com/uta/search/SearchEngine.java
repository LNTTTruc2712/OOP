package com.uta.search;

import com.uta.query.GenericQuery;
import com.uta.specification.Specification;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;

public class SearchEngine<T> {
    private final List<T> data;

    public SearchEngine(List<T> data) {
        this.data = data != null ? new ArrayList<>(data) : new ArrayList<>();
    }

    public List<T> search(Specification<T> specification, Comparator<T> sorter, int page, int pageSize) {
        Predicate<T> filter = specification != null ? specification::isSatisfiedBy : _ -> true;
        GenericQuery<T> query = new GenericQuery<T>()
                .withFilter(filter)
                .withSorter(sorter)
                .withPagination(page * pageSize, pageSize);
        return query.execute(data);
    }

    public List<T> search(Specification<T> specification) {
        return search(specification, null, 0, Integer.MAX_VALUE);
    }

    public List<T> search(Specification<T> specification, Comparator<T> sorter) {
        return search(specification, sorter, 0, Integer.MAX_VALUE);
    }

    public List<T> searchAll(Comparator<T> sorter, int page, int pageSize) {
        return search(null, sorter, page, pageSize);
    }

    public T findMax(Comparator<T> comparator) {
        GenericQuery<T> query = new GenericQuery<>();
        return query.findMax(data, comparator);
    }

    public T findMin(Comparator<T> comparator) {
        GenericQuery<T> query = new GenericQuery<>();
        return query.findMin(data, comparator);
    }
}