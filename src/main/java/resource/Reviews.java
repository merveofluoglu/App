package resource;

import java.sql.Timestamp;

public class Reviews {

    private long review_id;
    private long user_id;
    private long seller_id;
    private long post_id;
    private double point_scale;
    private String description;
    private Timestamp create_date;
    private boolean is_deleted;
}
