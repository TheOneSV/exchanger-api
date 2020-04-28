package com.yotaku.exchanger.exchangerapi.web.util;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.PagedResources;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public final class LinkUtil {

    public static final String PAGE = "page";
    public static final String REL_NEXT = "next";
    public static final String REL_PREV = "prev";
    public static final String REL_FIRST = "first";
    public static final String REL_LAST = "last";

    private LinkUtil() {
        throw new AssertionError();
    }

    public static <T> PagedResources<T> createPagedModel(
            final Collection<T> content,
            final UriComponentsBuilder uriBuilder,
            final int page,
            final int totalPages,
            final int pageSize,
            final long totalElements) {

        final PagedResources.PageMetadata pageMetadata
                = new PagedResources.PageMetadata(pageSize, page, totalElements, totalPages);

        final List<Link> links = LinkUtil.createResourceLinks(uriBuilder,page, totalPages, pageSize);

        return new PagedResources<>(content, pageMetadata, links);
    }

    public static List<Link> createResourceLinks(final UriComponentsBuilder uriBuilder, final int page, final int totalPages,
                                 final int pageSize) {
        final List<Link> links = new ArrayList<>();

        if (hasNextPage(page, totalPages)) {
            final String uriForNextPage = constructNextPageUri(uriBuilder, page, pageSize);
            Link nextPageLink = new Link(uriForNextPage, LinkUtil.REL_NEXT);

            links.add(nextPageLink);
        }
        if (hasPreviousPage(page)) {
            final String uriForPrevPage = constructPrevPageUri(uriBuilder, page, pageSize);
            Link prevPageLink = new Link(uriForPrevPage, LinkUtil.REL_PREV);

            links.add(prevPageLink);
        }
        if (hasFirstPage(page)) {
            final String uriForFirstPage = constructFirstPageUri(uriBuilder, pageSize);
            Link firstPageLink = new Link(uriForFirstPage, LinkUtil.REL_FIRST);

            links.add(firstPageLink);
        }
        if (hasLastPage(page, totalPages)) {
            final String uriForLastPage = constructLastPageUri(uriBuilder, totalPages, pageSize);
            Link lastPageLink = new Link(uriForLastPage, LinkUtil.REL_LAST);

            links.add(lastPageLink);
        }

        return links;
    }

    private static String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page + 1)
                .replaceQueryParam("size", size)
                .build()
                .encode()
                .toUriString();
    }

    private static String constructPrevPageUri(final UriComponentsBuilder uriBuilder, final int page, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, page - 1)
                .replaceQueryParam("size", size)
                .build()
                .encode()
                .toUriString();
    }

    private static String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, 0)
                .replaceQueryParam("size", size)
                .build()
                .encode()
                .toUriString();
    }

    private static String constructLastPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int size) {
        return uriBuilder.replaceQueryParam(PAGE, totalPages - 1)
                .replaceQueryParam("size", size)
                .build()
                .encode()
                .toUriString();
    }

    private static boolean hasNextPage(final int page, final int totalPages) {
        return page < (totalPages - 1);
    }

    private static boolean hasPreviousPage(final int page) {
        return page > 0;
    }

    private static boolean hasFirstPage(final int page) {
        return hasPreviousPage(page);
    }

    private static boolean hasLastPage(final int page, final int totalPages) {
        return (totalPages > 1) && hasNextPage(page, totalPages);
    }

}
