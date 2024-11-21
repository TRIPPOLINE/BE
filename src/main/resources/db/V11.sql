use trip;

CREATE INDEX idx_area_gun_gu_type ON trip.spot (area_code, si_gun_gu_code, content_type_id);