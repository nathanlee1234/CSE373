package seamcarving.seamfinding;

import seamcarving.Picture;
import seamcarving.SeamCarver;
import seamcarving.energy.EnergyFunction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Dynamic programming implementation of the {@link SeamFinder} interface.
 *
 * @see SeamFinder
 * @see SeamCarver
 */
public class DynamicProgrammingSeamFinder implements SeamFinder {

    @Override
    public List<Integer> findHorizontal(Picture picture, EnergyFunction f) {
        double [][] graph = new double[picture.width()][picture.height()];
        ArrayList<Integer> res = new ArrayList<>();

        for (int i = 0; i < picture.height(); i++) {
            graph[0][i] = f.apply(picture, 0, i);
        }

        for (int i = 1; i < picture.width(); i++) {
            for (int j = 0; j < picture.height(); j++) {
                double min = Double.MAX_VALUE;
                for (int k = j - 1; k <= j + 1; k++) {
                    if (k >= 0 && k < picture.height()) min = Math.min(graph[i - 1][k], min);
                }
                graph[i][j] = min + f.apply(picture, i, j);
            }
        }

        double min = Double.MAX_VALUE;
        int minIndex = 0;

        for (int i = 0; i < picture.height(); i++) {
            if (graph[picture.width() - 1][i] < min) {
                min = graph[picture.width() - 1][i];
                minIndex = i;
            }
        }
        res.add(minIndex);

        for (int i = picture.width() - 2; i >= 0; i--) {
            min = Double.MAX_VALUE;
            int start = minIndex - 1, end = minIndex + 1;

            for (int j = start; j <= end; j++) {
                if (j >= 0 && j < picture.height() && graph[i][j] < min) {
                    min = graph[i][j];
                    minIndex = j;
                }
            }
            res.add(minIndex);
        }

        Collections.reverse(res);
        return res;
    }
}
