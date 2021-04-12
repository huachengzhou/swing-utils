package gr.h1

def list1 = []
def list2 = [1, 2, 3, 4]
if (!list1) {
    println("list1为null");
}
if (list2) {
    println("list2不为null");
}
def name1 = list1 ? list1.get(0) : "null值";
println(name1);


println "${list1?.get(0)}" ;