import im


class department:
    count = 0
    name = ""

    def dptPrint(self):
        print("来吧，第一个引用，执行：", self)


dpt = department
dpt.dptPrint("正在执行···")
im.testImport.test(1)