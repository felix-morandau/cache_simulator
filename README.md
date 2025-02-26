# Cache Simulator

## Overview
This **Cache Simulator** is a Java-based application designed to emulate cache memory operations, providing insights into cache configuration, replacement policies, and memory management. The simulator allows users to configure cache parameters, execute memory read/write operations, and observe cache behavior.

## Key Features
- **Configurable Cache System:**
  - Supports **fully associative**, **direct-mapped**, and **set-associative** caches.
  - Allows setting **cache size, block size, and address width**.
- **Write Policies:**
  - Implements **Write-Back**, **Write-Through**, and **Write-Around** mechanisms.
- **Replacement Policies:**
  - Supports **FIFO (First-In-First-Out)**, **LRU (Least Recently Used)**, **LFU (Least Frequently Used)**, and **Random Replacement**.
- **Step-by-Step Simulation:**
  - Provides a **visual explanation** of cache operations (hits, misses, evictions).
- **Cache & Memory Visualization:**
  - Displays **valid bits, dirty bits, and actual data** in cache.
  - Showcases **physical memory updates**.
- **Interactive Operations:**
  - Perform **manual read and write** operations on memory.
  - Observe cache behavior with every action.

## Usage
1. **Launch the Simulator:**
   - Start the application to access the main UI.
2. **Configure Cache Parameters:**
   - Set **cache size, block size, associativity, and policies**.
3. **Execute Read/Write Operations:**
   - Input memory addresses and values to interact with the cache.
4. **Visualize Results:**
   - Observe **cache hits, misses, evictions, and memory updates**.
5. **Step-By-Step Mode:**
   - Execute operations gradually to understand cache mechanics.
6. **Reset Simulation:**
   - Clear cache memory and restart the simulation.

## Design & Implementation

### System Architecture
The system follows the **MVC pattern**:
- **Model:** Defines core components such as **Cache, Memory, and Policies**.
- **View:** Provides a **Java Swing-based GUI** for user interaction.
- **Controller:** Manages **cache operations, simulations, and updates**.

### Class Structure
- **Model Package:**
  - `CacheMemory`: Represents cache with its sets and lines.
  - `MainMemory`: Models physical memory.
  - `Address`: Handles memory addressing logic.
- **Service Package:**
  - `CacheManager`: Manages cache operations (hits, misses, evictions).
  - `MemoryAccessManager`: Handles read/write requests.
- **Util Package:**
  - Implements replacement and write policies (**FIFO, LRU, LFU, Random**).
- **View Package:**
  - `CacheMemoryPanel`: Displays cache contents.
  - `MainMemoryPanel`: Shows main memory values.
  - `SimulationMessagesPanel`: Logs simulation steps.

## Future Improvements
- **Advanced UI:** Improve visualization with real-time graphs.
- **Performance Metrics:** Display cache efficiency statistics.
- **Web Integration:** Implement a web-based interface.
- **Extended Policies:** Support additional cache strategies.

## References
- [Cache Memory Basics](https://www.cs.umd.edu/~meesh/411/CA-online/chapter/basics-of-cache-memory/index.html)
- [Cache Replacement Policies](https://courses.cs.washington.edu/courses/cse351/cachesim/)
- [Computer Architecture: Patterson & Hennessy](https://www.elsevier.com/books/computer-organization-and-design/9780124077263)

