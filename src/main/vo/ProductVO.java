package vo;

import javax.xml.crypto.Data;
import java.sql.Date;

public class ProductVO {
    private int productId;
    private String productName;
    private String productDescription;
    private String category;
    private String productAvail;
    private int listPrice;
    private Data productImage;
    private String mimeType;
    private String fileName;
    private String imageLastUpdate;

    public ProductVO(int productId, String productName, String productDescription, String category, String productAvail,
                     int listPrice, Data productImage, String mimeType, String fileName, String imageLastUpdate) {

        this.productId = productId;
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.productAvail = productAvail;
        this.listPrice = listPrice;
        this.productImage = productImage;
        this.mimeType = mimeType;
        this.fileName = fileName;
        this.imageLastUpdate = imageLastUpdate;
    }

    public ProductVO(String productName, String productDescription, String category, int listPrice,
                     String fileName) {
        this.productName = productName;
        this.productDescription = productDescription;
        this.category = category;
        this.listPrice = listPrice;
        this.fileName = fileName;
//        this.imageLastUpdate = imageLastUpdate;
    }

    public String getProductName() {
        return productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public String getCategory() {
        return category;
    }

    public int getListPrice() {
        return listPrice;
    }

    public String getFileName() {
        return fileName;
    }

    public String getImageLastUpdate() {
        return imageLastUpdate;
    }
}
