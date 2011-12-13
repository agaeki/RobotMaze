(ns CljExplorer.explore
  (import uk.ac.warwick.dcs.maze.logic.IRobot)
  (:gen-class
    :name CljExplorer
    :methods 
    [#^{:static true} [deadEnd [IRobot] int]]
    [#^{:static true} [nonWallExits [IRobot] int]]
    [#^{:static true} [corridor [IRobot] int]]
    [#^{:static true} [junction [IRobot] int]]))

(defn nonWallExits [^IRobot robot] (let [north (.look robot (. IRobot NORTH)) east (.look robot (. IRobot EAST)) south (.look robot (. IRobot SOUTH)) west (.look robot (. IRobot WEST))] 
                                     (count (filter (fn [x] (not (= x (. IRobot WALL)))) '(north east south west)))))

(defn deadEnd [^IRobot robot] (first (filter (fn [x] (not (= (val x) (. IRobot WALL)))) {:north (. robot look (. IRobot NORTH)) :east (. robot look (. IRobot EAST)) :south (. robot look (. IRobot SOUTH)) :west (. robot look (. IRobot WEST))})))

(defn corridor [^IRobot robot] (first (filter (fn [x] (not (= (val x) (. IRobot WALL)))) {:ahead (. robot look (. IRobot AHEAD)) :left (.robot look (. IRobot LEFT)) :right (.robot look (. IRobot RIGHT))})))

(defn junction [^IRobot robot] [let directions (vector (. IRobot AHEAD) (. IRobot LEFT) (. IRobot RIGHT)) beenBefores (filter (fn [x] (= x (. IRobot BEENBEFORE))) directions) notWalls (filter (fn [x] (= x (. IRobot WALL))))] (if (< 1 (count beenBefores)) 
                                                                                                                                                                                                                                   (nth beenBefores (rand-int (count beenBefores)))
                                                                                                                                                                                                                                   (if (= 1 (count beenBefores))
                                                                                                                                                                                                                                     (first beenBefores)
                                                                                                                                                                                                                                     (nth notWalls (rand-int (count beenBefores))))))