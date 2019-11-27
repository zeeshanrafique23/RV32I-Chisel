package MyCore
import chisel3._
import chisel3.util.Cat

class ALUcontrol extends Module{
    val io = IO(new Bundle{
        val aluOP = Input(UInt(3.W))
        val fun_3 = Input(UInt(3.W))
        val fun_7 = Input(UInt(1.W))

        val aluControl = Output(UInt(5.W))
    })

    io.aluControl := 0.U

    when (io.aluOP === 0.U && io.fun_7 === 0.U){
        io.aluControl := Cat(0.U , io.fun_3)              //R-Type

    }.elsewhen(io.aluOP === 0.U && io.fun_7 === 1.U){
        io.aluControl := Cat(1.U , io.fun_3)

    }.elsewhen(io.aluOP === 1.U && io.fun_3 === 5.U && io.fun_7 === 0.U){      //SRLI
        io.aluControl := Cat(0.U , io.fun_3)                            

    }.elsewhen(io.aluOP === 1.U && io.fun_3 === 5.U && io.fun_7 === 1.U){      //SRAI
        io.aluControl := Cat(1.U , io.fun_3)

    }.elsewhen(io.aluOP === 1.U && io.fun_3 === 1.U){              //SLLI
        io.aluControl := Cat(0.U , io.fun_3)

    }.elsewhen(io.aluOP === 1.U){                             //All Remaining I-Type
        io.aluControl := Cat(0.U , io.fun_3)
    
    }.elsewhen(io.aluOP === 2.U){                      
        io.aluControl := Cat(2.U , io.fun_3)              //Branch

    }.elsewhen(io.aluOP === 3.U){
        io.aluControl := 31.U                          //JALR , JAL

    }.otherwise{
        io.aluControl := 0.U                           //ADD
    }
}