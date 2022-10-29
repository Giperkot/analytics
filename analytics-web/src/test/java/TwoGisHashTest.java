import dto.parser.KeyValue;

import java.util.ArrayList;
import java.util.List;

public class TwoGisHashTest {

    // w = 5381
    // C = 33
    private static long calcHash(String e, int w, int C) {
        long t = e.length();
        int s = w;
        long temp = s;
        for (int r = 0; r < t; r += 1) {
            s = (int)(temp * C + e.charAt(r));

            if (s < 0) {
                temp = Long.parseLong(Integer.toBinaryString(s), 2);
            } else {
                temp = s;
            }
        }
        return temp;
    }

    public static void main(String[] args) {

        List<KeyValue> keyValues = new ArrayList<>();

        keyValues.add(new KeyValue("fields", "items.locale,items.flags,search_attributes,items.adm_div,items.city_alias,items.region_id,items.segment_id,items.reviews,items.point,request_type,context_rubrics,query_context,items.links,items.name_ex,items.org,items.group,items.dates,items.external_content,items.contact_groups,items.comment,items.ads.options,items.email_for_sending.allowed,items.stat,items.stop_factors,items.description,items.geometry.centroid,items.geometry.selection,items.geometry.style,items.timezone_offset,items.context,items.level_count,items.address,items.is_paid,items.access,items.access_comment,items.for_trucks,items.is_incentive,items.paving_type,items.capacity,items.schedule,items.floors,ad,items.rubrics,items.routes,items.platforms,items.directions,items.barrier,items.reply_rate,items.purpose,items.attribute_groups,items.route_logo,items.has_goods,items.has_apartments_info,items.has_pinned_goods,items.has_realty,items.has_exchange,items.has_payments,items.has_dynamic_congestion,items.is_promoted,items.congestion,items.delivery,items.order_with_cart,search_type,items.has_discount,items.metarubrics,broadcast,items.detailed_subtype,items.temporary_unavailable_atm_services,items.poi_category,items.structure_info.material,items.structure_info.floor_type,items.structure_info.gas_type,items.structure_info.year_of_construction,items.structure_info.elevators_count,items.structure_info.is_in_emergency_state,items.structure_info.project_type"));
        keyValues.add(new KeyValue("id", "2252435468848811"));
        keyValues.add(new KeyValue("key", "rurbbn3446"));
        keyValues.add(new KeyValue("locale", "ru_RU"));
        keyValues.add(new KeyValue("viewpoint1", "55.70136996182143,58.170571763867045"));
        keyValues.add(new KeyValue("viewpoint2", "56.77803003817857,57.83762823613296"));
        keyValues.add(new KeyValue("stat[sid]", "4cf3d884-b693-48f6-8385-82fe209c86ba"));
        keyValues.add(new KeyValue("stat[user]", "85eec556-7ea0-4ff6-a612-f46053fc682f"));
        keyValues.add(new KeyValue("shv", "2022-09-26-12"));

        keyValues.sort((a, b) -> {
            return a.key.compareTo(b.key);
        });

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < keyValues.size(); i++) {
            sb.append(keyValues.get(i).value);
        }

        String str = "/3.0/items/byid" + sb.toString() + "baf4c54e9dae";
        String layout = "/3.0/items/byiditems.locale,items.flags,search_attributes,items.adm_div,items.city_alias,items.region_id,items.segment_id,items.reviews,items.point,request_type,context_rubrics,query_context,items.links,items.name_ex,items.org,items.group,items.dates,items.external_content,items.contact_groups,items.comment,items.ads.options,items.email_for_sending.allowed,items.stat,items.stop_factors,items.description,items.geometry.centroid,items.geometry.selection,items.geometry.style,items.timezone_offset,items.context,items.level_count,items.address,items.is_paid,items.access,items.access_comment,items.for_trucks,items.is_incentive,items.paving_type,items.capacity,items.schedule,items.floors,ad,items.rubrics,items.routes,items.platforms,items.directions,items.barrier,items.reply_rate,items.purpose,items.attribute_groups,items.route_logo,items.has_goods,items.has_apartments_info,items.has_pinned_goods,items.has_realty,items.has_exchange,items.has_payments,items.has_dynamic_congestion,items.is_promoted,items.congestion,items.delivery,items.order_with_cart,search_type,items.has_discount,items.metarubrics,broadcast,items.detailed_subtype,items.temporary_unavailable_atm_services,items.poi_category,items.structure_info.material,items.structure_info.floor_type,items.structure_info.gas_type,items.structure_info.year_of_construction,items.structure_info.elevators_count,items.structure_info.is_in_emergency_state,items.structure_info.project_type2252435468854539rurbbn3446ru_RU2022-09-26-124cb19e8f-5edd-44db-b4dc-7286a716e97679ff71f9-420f-491e-947a-41b99c7fb9a456.277215225054704,58.02224467641199456.31086077494531,58.011867323588baf4c54e9dae";
        System.out.println(str.equals(layout));

        int[] A = {22, 4147, 1234, 11};
        int C = A[0] + A[3], w = A[1] + A[2];

        // System.out.println("MAX_VALUE is " + Integer.MAX_VALUE + " " + Integer.toBinaryString(Integer.MAX_VALUE));
        /*System.out.println(-1 >>> 0);


        System.out.println("MAX_VALUE is " + Integer.MAX_VALUE);
        System.out.println("-1 is " + Integer.toBinaryString(-1));
        System.out.println("4294967295 is " + Long.toBinaryString(4294967295L));

        System.out.println("-2 is " + Integer.toBinaryString(-2));
        System.out.println("4294967294 is " + Long.toBinaryString(4294967294L));

        System.out.println(Long.parseLong(Integer.toBinaryString(-1), 2));*/

        System.out.println(calcHash(str, w, C));
    }
}
