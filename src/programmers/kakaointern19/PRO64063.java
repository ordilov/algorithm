package programmers.kakaointern19;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class PRO64063 {

    public long[] solution(long k, long[] room_number) {
        long[] answer = new long[room_number.length];
        TreeMap<Long, Long> reservedRooms = new TreeMap<>();

        for (int i = 0; i < room_number.length; i++) {
            List<Long> updates = new ArrayList<>();
            long room = room_number[i];
            while (reservedRooms.containsKey(room)) {
                updates.add(room);
                room = reservedRooms.get(room);
            }
            for (long update : updates) {
                reservedRooms.put(update, room);
            }
            reservedRooms.put(room, room + 1);
            answer[i] = room;
        }

        return answer;
    }
}