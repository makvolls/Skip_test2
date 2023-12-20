package API.DTO.ProvidersDTO;

public class MetaPermissions {
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public MetaPermissions(int page, int items, int pages, int count) {
        this.page = page;
        this.items = items;
        this.pages = pages;
        this.count = count;
    }

    public int page;
    public int items;
    public int pages;
    public int count;
}
