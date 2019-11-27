// See LICENSE.txt for license details.
package MyCore

import chisel3.iotesters.{Driver, TesterOptionsManager}
import utils.TutorialRunner

object Launcher {
  val examples = Map(
      "TypeDecode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new TypeDecode(), manager) {
          (c) => new TypeDecode_tb(c)
        }
      },
       "ControlDecode" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ControlDecode(), manager) {
          (c) => new ControlDecode_tb(c)
        }
      },
        "Control" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Control(), manager) {
          (c) => new Control_tb(c)
        }
      },
        "ALUcontrol" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALUcontrol(), manager) {
          (c) => new ALUcontrol_tb(c)
        }
      },
        "ALU" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ALU(), manager) {
          (c) => new ALU_tb(c)
        }
      },
        "ImmGen" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new ImmGen(), manager) {
          (c) => new ImmGen_tb(c)
        }
      },
      "InstrucMem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new InstrucMem(), manager) {
          (c) => new InstrucMem_tb(c)
        }
      },
      "DataMem" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new DataMem(), manager) {
          (c) => new DataMem_tb(c)
        }
      },
      "Regfile" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Regfile(), manager) {
          (c) => new Regfile_tb(c)
        }
      },
      "Top" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Top(), manager) {
          (c) => new Top_tb(c)
        }
      },
      "Jalr" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new Jalr(), manager) {
          (c) => new Jalr_tb(c)
        }
      },
      "PC" -> { (manager: TesterOptionsManager) =>
        Driver.execute(() => new PC(), manager) {
          (c) => new PC_tb(c)
        }
      }
  )
  def main(args: Array[String]): Unit = {
    TutorialRunner("MyCore", examples, args)
  }
}

