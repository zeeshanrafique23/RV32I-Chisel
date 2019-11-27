package MyCore
import chisel3._

class ALU extends Module{
    val io=IO(new Bundle{
        val oprndA     = Input(SInt(32.W))
        val oprndB     = Input(SInt(32.W))
        val aluControl = Input(UInt(5.W))

        val branch     = Output(UInt(1.W))
        val aluOUT     = Output(SInt(32.W))
    })

    io.aluOUT := 0.S

    val shamt = io.oprndB(4,0)

    io.branch := ((io.aluControl(4,3) === 2.U) & (io.aluOUT === 1.S)).asUInt
   
   
    when (io.aluControl === 0.U){
        io.aluOUT := io.oprndA + io.oprndB

    }.elsewhen(io.aluControl === 1.U){
        io.aluOUT := io.oprndA << shamt

    }.elsewhen(io.aluControl === 2.U){
        when(io.oprndA < io.oprndB){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 1.S
        }

    }.elsewhen(io.aluControl === 3.U){
        when((io.oprndA).asUInt < (io.oprndB).asUInt){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 1.S
        }

    }.elsewhen(io.aluControl === 4.U){
        io.aluOUT := io.oprndA ^ io.oprndB
    
    }.elsewhen(io.aluControl === 5.U){
        io.aluOUT := io.oprndA >> shamt
    
    }.elsewhen(io.aluControl === 6.U){
        io.aluOUT := io.oprndA | io.oprndB
    
    }.elsewhen(io.aluControl === 7.U){
        io.aluOUT := io.oprndA & io.oprndB

    }.elsewhen(io.aluControl === 8.U){
        io.aluOUT := io.oprndA - io.oprndB
    
    }.elsewhen(io.aluControl === 13.U){           //arithmetic shift
        io.aluOUT := io.oprndA >> shamt
    
    }.elsewhen(io.aluControl === 16.U){
        when(io.oprndA === io.oprndB){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 0.S
        }

    }.elsewhen(io.aluControl === 17.U){
          when(io.oprndA =/= io.oprndB){
              io.aluOUT := 1.S
        }.otherwise{
              io.aluOUT := 0.S
        }
        

    }.elsewhen(io.aluControl === 18.U){
        when(io.oprndA < io.oprndB){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 0.S
        }
        

    }.elsewhen(io.aluControl === 19.U){
        when(io.oprndA >= io.oprndB){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 0.S
        }
    

    }.elsewhen(io.aluControl === 20.U){
        when((io.oprndA).asUInt < (io.oprndB).asUInt){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 0.S
        }

    }.elsewhen(io.aluControl === 21.U){
        when((io.oprndA).asUInt >= (io.oprndB).asUInt){
            io.aluOUT := 1.S
        }.otherwise{
            io.aluOUT := 0.S
        }
    
    }.elsewhen(io.aluControl === 31.U){
        io.aluOUT := io.oprndA 

    }
}